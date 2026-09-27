#!/bin/bash
set -e

if [ $# -eq 0 ]; then
    ./docker-run.sh bash -c '
        source poky/oe-init-build-env build-rpi3
        exec bash
    '
else
    ./docker-run.sh bash -c '
        source poky/oe-init-build-env build-rpi3
        bitbake "$@"
    ' bash "$@"
fi
