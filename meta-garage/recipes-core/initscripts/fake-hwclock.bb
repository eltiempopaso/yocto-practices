SUMMARY = "Fake Hardware Clock (fake-hwclock)"
DESCRIPTION = "Save and restore system clock on machines without a real-time clock (RTC)."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://fake-hwclock"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/fake-hwclock ${D}${sbindir}/fake-hwclock
}

FILES:${PN} += "${sbindir}/fake-hwclock"
