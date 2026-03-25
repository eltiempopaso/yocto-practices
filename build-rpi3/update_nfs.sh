#!/bin/bash
# setup-nfs-rootfs-rsync.sh
# Sincroniza el rootfs Yocto con el directorio NFS usando rsync
# Uso: ./setup-nfs-rootfs-rsync.sh /ruta/al/nfs/dir

set -e

if [ $# -ne 1 ]; then
    echo "Uso: $0 <directorio_destino_NFS>"
    exit 1
fi

NFS_DIR="$1"
YOCTO_ROOTFS_TAR="/local/work/repos/yocto-practices/build-rpi3/tmp/deploy/images/raspberrypi3-64/uri-image-raspberrypi3-64.rootfs.tar.bz2"
TMP_DIR="/tmp/yocto-rootfs"

# Limpiar tmp si existe
rm -rf "$TMP_DIR"
mkdir -p "$TMP_DIR"

# Extraer rootfs temporalmente
echo "Extrayendo rootfs Yocto en $TMP_DIR..."
tar xjf "$YOCTO_ROOTFS_TAR" -C "$TMP_DIR"

# Crear destino NFS si no existe
mkdir -p "$NFS_DIR"

# Rsync al NFS
echo "Sincronizando rootfs con $NFS_DIR..."
sudo rsync -aHAX --delete "$TMP_DIR"/ "$NFS_DIR"/

# Limpiar temporal
rm -rf "$TMP_DIR"

echo "Rootfs sincronizado en $NFS_DIR. Listo para NFS."
