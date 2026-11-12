#!/bin/bash
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

IMAGE_NAME="${1:-chess-station-image}"
if [ "$#" -gt 1 ]; then
    echo "Usage: $0 [image-name]" >&2
    exit 2
fi

NFS_DIR="/local/nfs/rpi3"
YOCTO_ROOTFS_TAR="$SCRIPT_DIR/build-rpi3/tmp/deploy/images/raspberrypi3-64/${IMAGE_NAME}-raspberrypi3-64.rootfs.tar.bz2"
TMP_DIR="/tmp/yocto-rootfs-rpi3"
WPA_SUPPLICANT_CONF="/local/nfs/config-rpi3/wpa_supplicant.conf"

echo "==> Yocto rootfs:"
echo "    $YOCTO_ROOTFS_TAR"
echo
echo "==> NFS directory:"
echo "    $NFS_DIR"
echo

# Check that the Yocto rootfs exists
if [ ! -f "$YOCTO_ROOTFS_TAR" ]; then
    echo "ERROR: Rootfs not found:"
    echo "       $YOCTO_ROOTFS_TAR"
    echo
    echo "Build it first with:"
    echo "       ./rpi3-build.sh $IMAGE_NAME"
    exit 1
fi

if [ ! -s "$WPA_SUPPLICANT_CONF" ]; then
    echo "ERROR: Wi-Fi configuration not found or empty:"
    echo "       $WPA_SUPPLICANT_CONF"
    exit 1
fi

# Clean and recreate the temporary directory
echo "==> Cleaning temporary directory..."
sudo rm -rf "$TMP_DIR"
sudo mkdir -p "$TMP_DIR"

# Extract the Yocto rootfs
echo "==> Extracting rootfs..."
sudo tar xjf "$YOCTO_ROOTFS_TAR" -C "$TMP_DIR"

# Create the NFS directory if it does not exist
echo "==> Creating NFS directory..."
sudo mkdir -p "$NFS_DIR"

# Synchronize the rootfs to the NFS directory
echo "==> Synchronizing rootfs..."
sudo rsync -aHAX --delete \
    "$TMP_DIR"/ \
    "$NFS_DIR"/

# Install Wi-Fi credentials after rsync so they do not have to be in the image.
echo "==> Installing wpa_supplicant configuration..."
sudo install -o root -g root -m 0600 \
    "$WPA_SUPPLICANT_CONF" \
    "$NFS_DIR/etc/wpa_supplicant.conf"

# Clean up the temporary directory
echo "==> Cleaning temporary directory..."
sudo rm -rf "$TMP_DIR"

echo
echo "==> NFS rootfs updated successfully."
echo "    $NFS_DIR"
