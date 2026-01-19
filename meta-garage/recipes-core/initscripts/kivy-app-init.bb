SUMMARY = "Hello Kivy autostart app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "file://hello_kivy.py \
          file://kivy-app"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "kivy-app"
INITSCRIPT_PARAMS = "defaults 99"

RDEPENDS:${PN} += "python3 python3-kivy"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/hello_kivy.py ${D}${bindir}/hello_kivy.py

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/kivy-app ${D}${sysconfdir}/init.d/kivy-app
}

