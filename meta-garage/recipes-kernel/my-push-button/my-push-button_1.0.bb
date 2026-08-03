SUMMARY = "Push button using irqs kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a8b093d723bf3a25a2a021ff6d1f334"

inherit module


SRC_URI = "file://Makefile \
           file://my-push-button.c \
           file://LICENSE"

S = "${WORKDIR}"

DEPENDS += "virtual/kernel"

RPROVIDES:${PN} += "kernel-module-my-push-button"
