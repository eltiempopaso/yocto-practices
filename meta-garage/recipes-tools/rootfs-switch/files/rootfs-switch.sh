#!/bin/bash
# rootfs-switch.sh
# Switch rootfs between SD and NFS by updating cmdline.txt

set -e

ETC_DIR="/etc/rootfs-switch"
BOOT_DIR="/boot"

usage() {
    echo "Usage: $0 <sd|nfs>"
    echo "  sd  -> Boot from SD rootfs"
    echo "  nfs -> Boot from NFS rootfs"
    exit 1
}

if [ $# -ne 1 ]; then
    usage
fi

ACTION="$1"

case "$ACTION" in
    sd)
        echo "Switching to SD rootfs..."
	mount /dev/mmcblk0p1 /boot/
        cp "$ETC_DIR/cmdline_sd.txt" "$BOOT_DIR/cmdline.txt"
        echo "Done. Reboot the RPi to boot from SD."
        ;;

    nfs)
        echo "Switching to NFS rootfs..."
	mount /dev/mmcblk0p1 /boot/
        cp "$ETC_DIR/cmdline_nfs.txt" "$BOOT_DIR/cmdline.txt"
        echo "Done. Reboot the RPi to boot from NFS."
        ;;

    *)
        usage
        ;;
esac
