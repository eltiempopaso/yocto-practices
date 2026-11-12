SUMMARY = "Wi-Fi interface startup script"
DESCRIPTION = "Bring up wlan0 using wpa_supplicant and DHCP"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://wifi-init"
S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "wifi-init"
INITSCRIPT_PARAMS = "defaults 20"

RDEPENDS:${PN} += "init-ifupdown wpa-supplicant busybox-udhcpc"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wifi-init ${D}${sysconfdir}/init.d/wifi-init
}
