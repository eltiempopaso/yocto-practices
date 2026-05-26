SUMMARY = "Nunchuk kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a8b093d723bf3a25a2a021ff6d1f334"

inherit module


SRC_URI = "file://Makefile \
           file://nunchuk.c \
           file://LICENSE"

S = "${WORKDIR}"

DEPENDS += "virtual/kernel"

RPROVIDES:${PN} += "kernel-module-nunchuk"
