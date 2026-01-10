SUMMARY = "Hello world kernel module bootlin exercices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "file://hello_version.c \
           file://Makefile"

S = "${WORKDIR}"

# Autocarga al arranque (recomendado)
#KERNEL_MODULE_AUTOLOAD:${PN} = "hello_version"

