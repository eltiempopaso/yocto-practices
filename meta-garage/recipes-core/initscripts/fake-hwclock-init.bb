SUMMARY = "Fake-hwclock autostart script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://fake-hwclock-init"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "fake-hwclock-init"
INITSCRIPT_PARAMS = "defaults 10"

RDEPENDS:${PN} += "fake-hwclock"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/fake-hwclock-init ${D}${sysconfdir}/init.d/fake-hwclock-init
}
