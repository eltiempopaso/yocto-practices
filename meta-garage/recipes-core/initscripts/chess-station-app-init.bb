SUMMARY = "Chess Station application autostart script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://chess-station-app-init"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "chess-station-app-init"
INITSCRIPT_PARAMS = "defaults 99"

RDEPENDS:${PN} += "busybox chess-station-app"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/chess-station-app-init ${D}${sysconfdir}/init.d/chess-station-app-init
}
