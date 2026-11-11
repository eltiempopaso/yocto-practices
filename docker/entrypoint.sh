#!/bin/bash
set -e

HOST_USER="${HOST_USER:-yocto}"
HOST_UID="${HOST_UID:-}"
HOST_GID="${HOST_GID:-}"

if [ -n "$HOST_UID" ] && [ -n "$HOST_GID" ]; then
    TARGET_USER="$HOST_USER"

    if getent passwd "$HOST_UID" >/dev/null 2>&1; then
        TARGET_USER="$(getent passwd "$HOST_UID" | cut -d: -f1)"
    elif getent passwd "$HOST_USER" >/dev/null 2>&1; then
        TARGET_USER="$HOST_USER"
    else
        if ! getent group "$HOST_GID" >/dev/null 2>&1; then
            groupadd --gid "$HOST_GID" "$HOST_USER" >/dev/null 2>&1 || true
        fi

        if ! getent passwd "$HOST_USER" >/dev/null 2>&1; then
            useradd --uid "$HOST_UID" --gid "$HOST_GID" --create-home --shell /bin/bash "$HOST_USER" >/dev/null 2>&1 || true
        fi

        TARGET_USER="$HOST_USER"
    fi

    if ! getent passwd "$TARGET_USER" >/dev/null 2>&1; then
        echo "Unable to resolve a valid user for UID $HOST_UID" >&2
        exit 1
    fi

    export HOME="/home/$TARGET_USER"
    exec gosu "$TARGET_USER" "$@"
fi

exec "$@"
