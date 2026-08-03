#include <linux/module.h>
#include <linux/platform_device.h>
#include <linux/gpio/consumer.h>
#include <linux/of.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Oriol Parera");
MODULE_DESCRIPTION("Simple Push Button Driver");

struct push_button {
    struct gpio_desc *button;
};

static int push_button_probe(struct platform_device *pdev)
{
    struct push_button *pb;
    int value;

    pb = devm_kzalloc(&pdev->dev, sizeof(*pb), GFP_KERNEL);
    if (!pb)
        return -ENOMEM;

    /*
     * Reads "button-gpios" from the Device Tree.
     */
    pb->button = devm_gpiod_get(&pdev->dev, "button", GPIOD_IN);
    if (IS_ERR(pb->button))
        return PTR_ERR(pb->button);

    value = gpiod_get_value(pb->button);

    dev_info(&pdev->dev,
             "Push button initialized. Current value = %d\n",
             value);

    platform_set_drvdata(pdev, pb);

    return 0;
}

static int push_button_remove(struct platform_device *pdev)
{
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

