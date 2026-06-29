LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#SRC_URI = "https://downloads.sourceforge.net/project/ninvaders/ninvaders/ninvaders-${PV}.tar.gz"
SRC_URI = "\
    https://downloads.sourceforge.net/project/ninvaders/ninvaders/ninvaders-${PV}.tar.gz \
    file://joystick-support.patch \
"
SRC_URI[sha256sum] = "bfbc5c378704d9cf5e7fed288dac88859149bee5ed0850175759d310b61fd30b"



DEPENDS = "ncurses"

EXTRA_OEMAKE = '\
    CC="${CC}" \
    CFLAGS="${CFLAGS} -fcommon" \
    LDFLAGS="${LDFLAGS}" \
'

do_configure() {
    :
}

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 nInvaders ${D}${bindir}/nInvaders
}
