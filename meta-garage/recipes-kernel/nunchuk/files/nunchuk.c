#include <linux/module.h>
#include <linux/i2c.h>
#include <linux/kernel.h>

static const struct i2c_device_id nunchuk_id[] = {
    { "nunchuk_device", 0 },
    { }
};
MODULE_DEVICE_TABLE(i2c, nunchuk_id);

//static const struct of_device_id adxl345_of_match[] = {
// { .compatible = "adi,adxl345" },
// { .compatible = "adi,adxl375" },
// { },
//};
//MODULE_DEVICE_TABLE(of, adxl345_of_match);


static int nunchuk_probe(struct i2c_client *client)
{
    pr_info("nunchuk device probed\n");
    return 0;
}

static void nunchuk_remove(struct i2c_client *client)
{
    pr_info("nunchuk device removed\n");
}

static struct i2c_driver nunchuk_driver = {
    .driver = {
        .name = "nunchuk_driver",
	//.of_match_table = adxl345_of_match,
    },
    .probe = nunchuk_probe,
    .remove = nunchuk_remove,
    .id_table = nunchuk_id,
};

module_i2c_driver(nunchuk_driver);

MODULE_LICENSE("GPL");

