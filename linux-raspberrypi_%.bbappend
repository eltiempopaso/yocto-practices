#FILESEXTRAPATHS:prepend := "${THISDIR}/linux-raspberrypi:"

# 1. Archivos que Yocto debe copiar automáticamente al ${WORKDIR}
#SRC_URI += "file://garage.cfg"
#SRC_URI += "file://nunchuk.dts"

# 2. Le indicamos a Yocto el archivo final que debe buscar para el despliegue
#KiERNEL_DEVICETREE:append = " overlays/nunchuk.dtbo"

# 3. Compilación manual nativa usando las herramientas del entorno de Yocto
#do_compile:append() {
#    bbnote "Compilando overlay nunchuk personalizado..."
#
#    # Creamos las carpetas de destino dentro del directorio de compilación activo (${B})
#    mkdir -p ${B}/arch/arm64/boot/dts/overlays
#    mkdir -p ${B}/arch/arm/boot/dts/overlays
#
#    # Compilamos el dts (que Yocto ya habrá dejado en ${WORKDIR}) usando el 'dtc' del sistema
#    dtc -@ -I dts -O dtb -o ${B}/arch/arm64/boot/dts/overlays/nunchuk.dtbo ${WORKDIR}/nunchuk.dts

#    # Copiamos el resultado a la ruta de 32 bits por compatibilidad con los scripts de meta-raspberrypi
#    cp ${B}/arch/arm64/boot/dts/overlays/nunchuk.dtbo ${B}/arch/arm/boot/dts/overlays/nunchuk.dtbo
#}


FILESEXTRAPATHS:prepend := "${THISDIR}/linux-raspberrypi:"

# 1. Traemos los archivos al WORKDIR
SRC_URI += "file://garage.cfg"
SRC_URI += "file://nunchuk.dts"

# NOTA: Eliminamos KERNEL_DEVICETREE:append para que 'make' no se queje de que no sabe construirlo.

# 2. Compilamos nuestro .dtbo de forma independiente al ciclo principal de make
do_compile:append() {
    bbnote "Compilando overlay nunchuk independiente..."
    dtc -@ -I dts -O dtb -o ${WORKDIR}/nunchuk.dtbo ${WORKDIR}/nunchuk.dts
}

# 3. Lo inyectamos a mano directamente en el directorio donde Yocto empaqueta los Device Trees
do_install:append() {
    bbnote "Instalando overlay nunchuk en el directorio de despliegue..."

    # Creamos la carpeta de overlays en el directorio de instalación si no existe
    mkdir -p ${D}/boot/overlays

    # Copiamos nuestro binario compilado directamente ahí
    cp ${WORKDIR}/nunchuk.dtbo ${D}/boot/overlays/nunchuk.dtbo
}
