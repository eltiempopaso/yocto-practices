SUMMARY = "Chrony autostart script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://chrony-init"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "chrony-init"
INITSCRIPT_PARAMS = "defaults 20"

RDEPENDS:${PN} += "chrony"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/chrony-init ${D}${sysconfdir}/init.d/chrony-init
}
