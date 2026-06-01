FILESEXTRAPATHS:prepend := "${THISDIR}/rpi-config:"

SRC_URI += "file://nunchuk.dts \
            file://nunchuk.yaml"

ENABLE_I2C = "1"

RPI_EXTRA_CONFIG:append = "\ndtoverlay=nunchuk\n"

#KERNEL_MODULE_AUTOLOAD += " nunchuk"

KERNEL_DEVICETREE:append = "overlays/nunchuk.dtbo"

#python () {

#    bb.plain("==================================================")

#    bb.plain("  [MI CAPA] Cargando meta-garage raspberrypi3-64.conf")

#    bb.plain("  ENABLE_I2C actual: %s" % d.getVar('ENABLE_I2C'))

#    bb.plain("  RPI_EXTRA_CONFIG actual: %s" % d.getVar('RPI_EXTRA_CONFIG'))

#    bb.plain("  KERNEL_DEVICETREE: %s" % d.getVar('KERNEL_DEVICETREE'))

#    bb.plain("==================================================")

#} 

DEPENDS += "dtc-native"

# =========================================================================
# 1. COMPILACIÓN
# =========================================================================
do_compile:append() {
    bbnote "=== GARAGE-OS: Compilando Device Tree Overlay ==="

    dtc -@ -I dts -O dtb \
        -o ${WORKDIR}/nunchuk.dtbo \
        ${WORKDIR}/nunchuk.dts

    bbnote "DTBO generado en ${WORKDIR}/nunchuk.dtbo"

#    bbnote "Running dt-schema validation"
#    export DT_SCHEMA_FILES="${WORKDIR}"
#    oe_runmake dtbs_check || true
}


# =========================================================================
# 2. INSTALL (rootfs)
# =========================================================================
#do_install:append() {
#    bbnote "=== GARAGE-OS: Instalando DT overlay en rootfs ==="
#
#    install -d ${D}/boot/overlays
#    install -m 0644 ${WORKDIR}/nunchuk.dtbo ${D}/boot/overlays/nunchuk.dtbo
#}


# =========================================================================
# 3. DEPLOY (artefactos de build)
# =========================================================================
do_deploy:append() {
    bbnote "=== GARAGE-OS: Desplegando DT overlay ==="

    install -m 0644 ${WORKDIR}/nunchuk.dtbo \
        ${DEPLOY_DIR_IMAGE}/nunchuk.dtbo
}


