#include <linux/module.h>
#include <linux/platform_device.h>
#include <linux/gpio/consumer.h>
#include <linux/of.h>
#include <linux/interrupt.h>
#include <linux/gpio.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Oriol Parera");
MODULE_DESCRIPTION("Simple Push Button Driver");

struct push_button {
    struct platform_device *pdev;
    struct gpio_desc *button;
    int irq;
};

void showContextInfo(int irq, struct push_button *pb) {
    dev_info(&pb->pdev->dev,"IRQ %d on CPU %u\n", irq, smp_processor_id());

    dev_info(&pb->pdev->dev,"current=%s pid=%d\n", current->comm, current->pid);

    dev_info(&pb->pdev->dev,"in_interrupt=%d in_irq=%d in_softirq=%d irqs_disabled=%d preempt=%x\n", in_interrupt(), in_irq(), in_softirq(), irqs_disabled(), preempt_count());

    dump_stack();
}

static irqreturn_t push_button_top(int irq, void *data)
{
    struct push_button *pb = data;
    dev_info(&pb->pdev->dev, "Top half. Button pressed\n");

    showContextInfo(irq, pb);
    return IRQ_WAKE_THREAD;
}

static irqreturn_t push_button_irq_thread(int irq, void *data) {
    struct push_button *pb = data;

    dev_info(&pb->pdev->dev, "Button pressed\n");

    showContextInfo(irq, pb);

    return IRQ_HANDLED;
}


static int push_button_probe(struct platform_device *pdev) {
    struct push_button *pb;
    int err;
    //int value;

    pb = devm_kzalloc(&pdev->dev, sizeof(*pb), GFP_KERNEL);
    if (!pb)
        return -ENOMEM;

    /*
     * Reads "button-gpios" from the Device Tree.
     */
    pb->button = devm_gpiod_get(&pdev->dev, "button", GPIOD_IN);
    if (IS_ERR(pb->button)) {
        dev_err(&pdev->dev, "devm_gpiod_get failed: %ld\n", PTR_ERR(pb->button));
        return PTR_ERR(pb->button);
    }

    pb->pdev = pdev;

    /*

    value = gpiod_get_value(pb->button);

    dev_info(&pdev->dev, "Push button initialized. Current value = %d\n", value);
    */


    /* platform_get_irq could be used if device tree was like this:
     *
	push_button {
	    compatible = "garage,my-push-button";

	    interrupts = <17 IRQ_TYPE_EDGE_FALLING>;
	    interrupt-parent = <&gpio>;
	};

	*/

    pb->irq = gpiod_to_irq(pb->button); 
    if (pb->irq < 0)
        return pb->irq;

    dev_info(&pdev->dev, "GPIO IRQ = %d\n", pb->irq);

    err = devm_request_threaded_irq(&pdev->dev,
                                pb->irq,
				push_button_top, // if NULLPTR no top half is called. only bottom.
                                push_button_irq_thread,
                                IRQF_TRIGGER_FALLING |
                                IRQF_ONESHOT,
                                "push_button",
                                pb);

    if (err < 0) {
	dev_err(&pdev->dev, "Can't get IRQ for push button: %d\n", err);
	return err;
    }


    platform_set_drvdata(pdev, pb);

    return 0;
}

static int push_button_remove(struct platform_device *pdev) {
    dev_info(&pdev->dev, "Push button removed\n");

    return 0;
}

static const struct of_device_id push_button_of_match[] = {
    { .compatible = "garage,my-push-button" },
    { }
};
MODULE_DEVICE_TABLE(of, push_button_of_match);

static struct platform_driver push_button_driver = {
    .probe = push_button_probe,
    .remove = push_button_remove,
    .driver = {
        .name = "push_button",
        .of_match_table = push_button_of_match,
    },
};

module_platform_driver(push_button_driver);

