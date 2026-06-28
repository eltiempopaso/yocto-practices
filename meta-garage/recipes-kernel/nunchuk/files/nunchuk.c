#include <linux/module.h>
#include <linux/i2c.h>
#include <linux/kernel.h>
#include <linux/delay.h>
#include <linux/input.h>

struct nunchuk_dev {
    struct i2c_client *i2c_client;
};

static const struct i2c_device_id nunchuk_id[] = {
    { "nunchuk_device", 0 },
    { }
};
MODULE_DEVICE_TABLE(i2c, nunchuk_id);

static const struct of_device_id nunchuk_of_match[] = {
    { .compatible = "nintendo,nunchuk" },
    { }
};
MODULE_DEVICE_TABLE(of, nunchuk_of_match);

//////////////////////////////////////

static int initialize_encrypted_nunchuk(struct i2c_client *client) {
        int status;
	u8 init1[] = { 0x40, 0x00 };

	status = i2c_master_send(client, init1, sizeof(init1));
	if (status < 0) return status; 

        return status;
}

static int initialize_unencrypted_nunchuk(struct i2c_client *client) {
        int status;
	u8 init1[] = { 0xf0, 0x55 };
	u8 init2[] = { 0xfb, 0x00 };

	status = i2c_master_send(client, init1, sizeof(init1));
	if (status < 0) return status; 

	fsleep(10000);

	status = i2c_master_send(client, init2, sizeof(init2));
	if (status < 0) return status; 

        return status;
}

static int nunchuk_read_registers(struct i2c_client *client, int * zpressed, int * cpressed) {
        int status;
	u8 req[]   = { 0x00 };
	u8 buf[6];

	fsleep(10000);
	status = i2c_master_send(client, req, sizeof(req));
	if (status < 0) return status; 

        fsleep(10000);
	status = i2c_master_recv(client, buf, sizeof(buf));
	if (status != sizeof(buf)) {
		dev_err(&client->dev, "failed to read values: %d\n", status);
		return status < 0 ? status : -EIO;
	}

	*zpressed = (buf[5] & 0x01)? 0 : 1;
	*cpressed = (buf[5] & 0x02)? 0 : 1;

	return 0;
}

static void nunchuk_poll(struct input_dev *input) {
    int status;
    int zpressed, cpressed;
    struct nunchuk_dev *nunchuk = input_get_drvdata(input);
    struct i2c_client *client = nunchuk->i2c_client;

    status = nunchuk_read_registers(client, &zpressed, &cpressed);

    if (status < 0) {
        dev_dbg(&client->dev, "poll error %d for '%s'\n", status, client->name);
	return;
    }

    input_report_key(input, BTN_Z, zpressed);
    input_report_key(input, BTN_C, cpressed);
    input_sync(input);
    pr_info("poll nunchuk: zpressed=%d cpressed=%d\n", zpressed, cpressed);
}

/////////////////////////////////////

static int nunchuk_probe(struct i2c_client *client) {
    int status;
    struct input_dev *input;
    struct nunchuk_dev *nunchuk;

    input = devm_input_allocate_device(&client->dev);
    if (!input) {
	    dev_err(&client->dev, "failed to allocate input device\n");
	    status = -ENOMEM;
	    goto fail;
    }

    input->name = "Wii Nunchuk";
    input->id.bustype = BUS_I2C;
    set_bit(EV_KEY, input->evbit);
    set_bit(BTN_C, input->keybit);
    set_bit(BTN_Z, input->keybit);

    nunchuk = devm_kzalloc(&client->dev, sizeof(*nunchuk), GFP_KERNEL);
    if (!nunchuk) {
        status = -ENOMEM;
	goto fail;
    }

    nunchuk->i2c_client = client;
    input_set_drvdata(input, nunchuk);

    status = input_setup_polling(input, nunchuk_poll);
    if (status) { 
	goto fail;
    }

    //input_set_poll_interval(input, 5000);
    //input_set_min_poll_interval(input, 5000);
    //input_set_max_poll_interval(input, 5000);
    const unsigned int poll_interval = 500; // TODO: move this config to devtree. also reduce to 50ms, now its 5s
    input_set_poll_interval(input, poll_interval);

    /* register input poll device */
    status = input_register_device(input);
    if (status) {
	dev_err(&client->dev, "failed to register input device: %d\n", status);
    	goto fail;
    }

    //TODO: check devtree parameter to decide if encrypted or unencrypted
    status = initialize_unencrypted_nunchuk( client );

    if (status < 0)
	    goto fail;

    return 0;

fail:
        dev_dbg(&client->dev, "probe error %d for '%s'\n", status, client->name);

        return status;
}

static void nunchuk_remove(struct i2c_client *client) {
    pr_info("nunchuk device removed\n");
}

static struct i2c_driver nunchuk_driver = {
    .driver = {
        .name = "nunchuk_driver",
	.of_match_table = nunchuk_of_match,
    },
    .probe = nunchuk_probe,
    .remove = nunchuk_remove,
    .id_table = nunchuk_id,
};

module_i2c_driver(nunchuk_driver);

MODULE_LICENSE("GPL");

