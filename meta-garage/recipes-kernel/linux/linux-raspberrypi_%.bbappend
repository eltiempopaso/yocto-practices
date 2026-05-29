FILESEXTRAPATHS:prepend := "${THISDIR}/linux-raspberrypi:"

SRC_URI += "file://garage.cfg"
SRC_URI += "file://nunchuk.dts"


# =========================================================================
# 1. FASE DE COMPILACIÓN (Se queda igual, cocinamos en WORKDIR)
# =========================================================================
do_compile:append() {
    bbnote "=== GARAGE-OS: Iniciando compilación de Device Tree Overlay ==="
    bbnote "Origen (.dts): ${WORKDIR}/nunchuk.dts"
    bbnote "Destino (.dtbo): ${WORKDIR}/nunchuk.dtbo"

    ${B}/scripts/dtc/dtc -@ -I dts -O dtb -o ${WORKDIR}/nunchuk.dtbo ${WORKDIR}/nunchuk.dts
}

# =========================================================================
# 2. FASE DE INSTALACIÓN (Alineada con el estándar oficial)
# =========================================================================
do_install:append() {
    bbnote "=== GARAGE-OS: Iniciando instalación en el Sysroot local ==="
    bbnote "Origen (.dtbo): ${WORKDIR}/nunchuk.dtbo"
    # ATENCIÓN: El destino oficial es directamente en ${D}/boot/
    bbnote "Destino (Sysroot): ${D}/boot/nunchuk.dtbo"

    mkdir -p ${D}/boot
    cp ${WORKDIR}/nunchuk.dtbo ${D}/boot/nunchuk.dtbo
}

# =========================================================================
# 3. FASE DE DESPLIEGUE (Donde realmente se guardan las imágenes)
# =========================================================================
do_deploy:append() {
    bbnote "=== GARAGE-OS: Iniciando despliegue en el directorio de imágenes ==="
    bbnote "Origen (.dtbo): ${WORKDIR}/nunchuk.dtbo"
    # ATENCIÓN: En el deploy final de la máquina sí van dentro de la subcarpeta 'overlays'
    bbnote "Destino (Deploy): ${DEPLOY_DIR_IMAGE}/overlays/nunchuk.dtbo"

    mkdir -p ${DEPLOY_DIR_IMAGE}/overlays
    cp ${WORKDIR}/nunchuk.dtbo ${DEPLOY_DIR_IMAGE}/nunchuk.dtbo
}

