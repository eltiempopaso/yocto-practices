#!/bin/bash
# flash-nfs-to-sd.sh
# Flash the SD card from the NFS rootfs (must be booted from NFS)

set -e

SD_DEVICE="/dev/mmcblk0p2"
INACTIVE_ROOT="/mnt/inactive"
ROOT_DEV=$(mount | grep ' / ' | awk '{print $1}' | grep 'nfs')

# Check that we're running from NFS
if [[ "$ROOT_DEV" == "" ]]; then
    echo "Error: you can only flash the SD if booted from NFS"
    exit 1
fi

# Check NFS root exists
mkdir -p $INACTIVE_ROOT
mount $SD_DEVICE $INACTIVE_ROOT

read -p "WARNING! This will overwrite the SD rootfs. Continue? (yes/no): " CONF
if [ "$CONF" != "yes" ]; then
    echo "Cancelled."
    exit 1
fi

echo "Flashing SD ($SD_DEVICE) with NFS rootfs..."
#rsync -aHAX --delete / "${INACTIVE_ROOT}/"
rsync -aHAX --delete --one-file-system / "${INACTIVE_ROOT}/"

echo "SD rootfs updated. You can now reboot and boot from SD."
