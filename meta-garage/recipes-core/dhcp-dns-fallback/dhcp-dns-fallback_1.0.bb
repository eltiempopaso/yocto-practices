SUMMARY = "Fallback DNS servers for DHCP clients"
DESCRIPTION = "Configure DNS from kernel DHCP data or use public fallback servers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://90-dns-fallback file://dns-resolver-init"
S = "${WORKDIR}"

RDEPENDS:${PN} += "busybox-udhcpc init-ifupdown"

inherit update-rc.d

INITSCRIPT_NAME = "dns-resolver-init"
INITSCRIPT_PARAMS = "start 15 2 3 4 5 ."

do_install() {
    install -d ${D}${sysconfdir}/udhcpc.d
    install -m 0755 ${WORKDIR}/90-dns-fallback ${D}${sysconfdir}/udhcpc.d/90-dns-fallback
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/dns-resolver-init ${D}${sysconfdir}/init.d/dns-resolver-init
}

FILES:${PN} += "${sysconfdir}/udhcpc.d/90-dns-fallback ${sysconfdir}/init.d/dns-resolver-init"
