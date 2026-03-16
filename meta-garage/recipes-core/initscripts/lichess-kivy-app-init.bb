SUMMARY = "Lichess Kivy App autostart script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://lichess-kivy-app-init"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "lichess-kivy-app-init"
INITSCRIPT_PARAMS = "defaults 99"

RDEPENDS:${PN} += "lichess-kivy-app"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lichess-kivy-app-init ${D}${sysconfdir}/init.d/lichess-kivy-app-init
}
