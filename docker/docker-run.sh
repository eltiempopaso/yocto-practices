#!/bin/bash
set -e

IMAGE="yocto-ubuntu24"

mkdir -p "$HOME/.dockerHome"
rsync -a "$HOME/.ssh/" "$HOME/.dockerHome/"

echo "==> Checking Docker image..."
docker build -t "$IMAGE" ./docker/.

docker run --rm -it \
    --user "$(id -u):$(id -g)" \
    -e HOME="/home/ubuntu" \
    -v "$HOME/.dockerHome:/home/ubuntu/.ssh" \
    -v "$(pwd):/work" \
    "$IMAGE" \
    "$@"
