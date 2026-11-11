#!/bin/bash
set -e

IMAGE="yocto-ubuntu24"
HOST_USER="${USER:-$(id -un)}"
HOST_UID="$(id -u)"
HOST_GID="$(id -g)"

mkdir -p "$HOME/.dockerHome"
rsync -a "$HOME/.ssh/" "$HOME/.dockerHome/"

echo "==> Checking Docker image..."
docker build -t "$IMAGE" ./docker/.

CONTAINER_HOME="$(docker run --rm \
    --entrypoint /bin/sh \
    -e HOST_UID="$HOST_UID" \
    "$IMAGE" \
    -c 'getent passwd "$HOST_UID" | cut -d: -f6')"
CONTAINER_HOME="${CONTAINER_HOME:-/home/$HOST_USER}"

docker run --rm -it \
    -e HOST_USER="$HOST_USER" \
    -e HOST_UID="$HOST_UID" \
    -e HOST_GID="$HOST_GID" \
    -e HOME="$CONTAINER_HOME" \
    -v "$HOME/.dockerHome:$CONTAINER_HOME/.ssh" \
    -v "$(pwd):/work" \
    "$IMAGE" \
    "$@"
