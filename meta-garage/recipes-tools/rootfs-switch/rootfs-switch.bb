DESCRIPTION = "Tools to switch rootfs between SD and NFS and flash SD"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

# CRITICAL: Added file://LICENSE to the SRC_URI below
SRC_URI = "file://rootfs-switch.sh \
           file://flash-nfs-to-sd.sh \
           file://cmdline_sd.txt \
           file://cmdline_nfs.txt \
           file://LICENSE"

RDEPENDS:${PN} += "\
    bash \
"

S = "${WORKDIR}"
ETC_PATH = "/etc/rootfs-switch/"

do_install() {
    # Install scripts into /usr/bin
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/rootfs-switch.sh ${D}${bindir}/rootfs-switch
    install -m 0755 ${WORKDIR}/flash-nfs-to-sd.sh ${D}${bindir}/flash-nfs-to-sd

    # Install cmdline files into /boot
    install -d ${D}${ETC_PATH}
    install -m 0644 ${WORKDIR}/cmdline_sd.txt  ${D}${ETC_PATH}/cmdline_sd.txt
    install -m 0644 ${WORKDIR}/cmdline_nfs.txt ${D}${ETC_PATH}/cmdline_nfs.txt
}

# Define the subpackage first in the priority list
#PACKAGES =+ "${PN}-boot"

# Assign specific files to the boot subpackage
#FILES:${PN}-boot = " \
#    /boot/cmdline_sd.txt \
#    /boot/cmdline_nfs.txt \
#"

# Assign the binaries to the main package
FILES:${PN} = " \
        ${bindir}/* \
        ${ETC_PATH}/* \
"
