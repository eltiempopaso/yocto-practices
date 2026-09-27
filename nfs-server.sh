#!/bin/bash
set -e

show_help()
{
    echo "Usage: $0 <platform> <action>"
    echo
    echo "Platforms:"
    echo "  rpi3"
    echo
    echo "Actions:"
    echo "  start"
    echo "  stop"
    echo "  status"
    echo
    echo "Examples:"
    echo "  $0 rpi3 start"
    echo "  $0 rpi3 stop"
    echo "  $0 rpi3 status"
}

if [ $# -eq 0 ]; then
    show_help
    exit 0
fi

PLATFORM="$1"
ACTION="${2:-}"

case "$PLATFORM" in

    rpi3)
        NFS_ROOT="/local/nfs/rpi3"
	NFS_NETWORK="192.168.0.0/24"
        EXPORT_LINE="$NFS_ROOT $NFS_NETWORK(rw,sync,no_subtree_check)"
        ;;

    *)
        echo "ERROR: Unknown platform: $PLATFORM"
        echo
        show_help
        exit 1
        ;;

esac

case "$ACTION" in

    start)
        echo "==> Creating NFS directory..."
        sudo mkdir -p "$NFS_ROOT"

        echo "==> Configuring /etc/exports..."

        if ! grep -Fxq "$EXPORT_LINE" /etc/exports; then
            echo "$EXPORT_LINE" | sudo tee -a /etc/exports >/dev/null
        fi

        echo "==> Reloading NFS exports..."
        sudo exportfs -ra

        echo "==> NFS started for $PLATFORM"
        sudo exportfs -v
        ;;

    stop)
        echo "==> Removing NFS export..."

        sudo sed -i "\|^$NFS_ROOT |d" /etc/exports

        sudo exportfs -ra

        echo "==> NFS export stopped for $PLATFORM"
        ;;

    status)
        echo "==> NFS exports:"
        sudo exportfs -v
        ;;

    *)
        echo "ERROR: Unknown or missing action: $ACTION"
        echo
        show_help
        exit 1
        ;;

esac

