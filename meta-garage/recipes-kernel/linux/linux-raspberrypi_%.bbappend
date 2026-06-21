FILESEXTRAPATHS:prepend := "${THISDIR}/linux-raspberrypi:"
#PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI += " \
    file://garage.cfg \
    file://0001-adding-nunchuk-overlay.patch \
"
#SRC_URI += " \
#    file://garage.cfg \
#    file://nunchuk-overlay.dts;subdir=git/arch/${ARCH}/boot/dts/overlays \
#"

KERNEL_DEVICETREE:append = " overlays/nunchuk.dtbo"
IMAGE_BOOT_FILES:append = " nunchuk.dtbo;overlays/nunchuk.dtbo"

#DEPENDS += "dtc-native"

# Ruta donde se generará el overlay compilado
#GARAGE_DTBO_PATH = "${B}/arch/arm64/boot/dts/overlays/nunchuk.dtbo"

# =========================================================================
# GENERACIÓN DEL DEVICE TREE OVERLAY
# =========================================================================

#do_configure:append() {
#   bbnote "=== GARAGE-OS: Generando Device Tree Overlay ==="
#   bbnote "Input DTS : ${WORKDIR}/nunchuk.dts"
#   bbnote "Output DTBO: ${GARAGE_DTBO_PATH}"
#    install -d $(dirname ${GARAGE_DTBO_PATH})
#    dtc -@ -I dts -O dtb \
#       -o ${GARAGE_DTBO_PATH} \
#       ${WORKDIR}/nunchuk.dts
#    if [ ! -f ${GARAGE_DTBO_PATH} ]; then
#       bbfatal "Failed to generate ${GARAGE_DTBO_PATH}"
#   fi
#    bbnote "DTBO generado correctamente: ${GARAGE_DTBO_PATH}"
#

#do_configure:prepend() {
#    install -m 0644 ${WORKDIR}/nunchuk.dts \
#        ${S}/arch/arm64/boot/dts/overlays/
#}

#do_configure:prepend() {
#    install -d ${S}/arch/arm64/boot/dts/overlays
#
#    install -m 0644 ${WORKDIR}/nunchuk.dts \
#        ${S}/arch/arm64/boot/dts/overlays/nunchuk.dts
#}

#do_configure:append() {¨
#echo "dtbo-y += nunchuk.dtbo" >> \¨
#${S}/arch/arm64/boot/dts/overlays/Makefile¨
#}



