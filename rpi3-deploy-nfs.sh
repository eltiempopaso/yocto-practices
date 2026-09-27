#!/bin/bash
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

NFS_DIR="/local/nfs/rpi3"
YOCTO_ROOTFS_TAR="$SCRIPT_DIR/build-rpi3/tmp/deploy/images/raspberrypi3-64/uri-image-raspberrypi3-64.rootfs.tar.bz2"
TMP_DIR="/tmp/yocto-rootfs-rpi3"

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
    echo "       ./build_rpi3 uri-image"
    exit 1
fi

# Clean and recreate the temporary directory
echo "==> Cleaning temporary directory..."
rm -rf "$TMP_DIR"
mkdir -p "$TMP_DIR"

# Extract the Yocto rootfs
echo "==> Extracting rootfs..."
tar xjf "$YOCTO_ROOTFS_TAR" -C "$TMP_DIR"

# Create the NFS directory if it does not exist
echo "==> Creating NFS directory..."
sudo mkdir -p "$NFS_DIR"

# Synchronize the rootfs to the NFS directory
echo "==> Synchronizing rootfs..."
sudo rsync -aHAX --delete \
    "$TMP_DIR"/ \
    "$NFS_DIR"/

# Clean up the temporary directory
echo "==> Cleaning temporary directory..."
rm -rf "$TMP_DIR"

echo
echo "==> NFS rootfs updated successfully."
echo "    $NFS_DIR"


