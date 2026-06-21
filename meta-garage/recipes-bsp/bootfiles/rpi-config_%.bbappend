FILESEXTRAPATHS:prepend := "${THISDIR}/rpi-config:"

SRC_URI += "file://nunchuk.dts \
            file://nunchuk.yaml"

ENABLE_I2C = "1"

RPI_EXTRA_CONFIG:append = "\ndtoverlay=nunchuk\n"

#KERNEL_MODULE_AUTOLOAD += " nunchuk"



