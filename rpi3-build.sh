#!/bin/bash
set -e

if [ $# -eq 0 ]; then
    ./docker/docker-run.sh bash -c '
        source poky/oe-init-build-env build-rpi3
        exec bash
    '
else
    ./docker/docker-run.sh bash -c '
        source poky/oe-init-build-env build-rpi3 > /dev/null
	BB_NUMBER_THREADS="4" PARALLEL_MAKE="-j 4" bitbake "$@"
    ' bash "$@"
fi
