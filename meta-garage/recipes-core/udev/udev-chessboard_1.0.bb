SUMMARY = "Udev rule for chessboard device"
LICENSE = "CLOSED"

SRC_URI += "file://99-chessboard.rules"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/99-chessboard.rules \
        ${D}${sysconfdir}/udev/rules.d/
}

FILES:${PN} += "${sysconfdir}/udev/rules.d/99-chessboard.rules"
