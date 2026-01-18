SUMMARY = "SysV init script to start Kivy app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://hello_kivy.py \
           file://kivy-app \
           file://xinitrc-kivy"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "kivy-app"
INITSCRIPT_PARAMS = "start 99 5 ."

do_install() {
    # App Kivy
    install -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/hello_kivy.py ${D}/usr/bin/hello_kivy.py

    # Sysvinit script
    install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/kivy-app ${D}/etc/init.d/kivy-app

    # xinitrc
    install -d ${D}/etc/X11/xinit
    install -m 0755 ${WORKDIR}/xinitrc-kivy ${D}/etc/X11/xinit/xinitrc-kivy
}


