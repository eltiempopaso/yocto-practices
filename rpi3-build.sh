#!/bin/bash
set -e

if [ $# -eq 0 ]; then
    ./docker/docker-run.sh bash -c '
        source poky/oe-init-build-env build-rpi3
        export BB_ENV_PASSTHROUGH_ADDITIONS="${BB_ENV_PASSTHROUGH_ADDITIONS:+$BB_ENV_PASSTHROUGH_ADDITIONS }BB_PRESSURE_MAX_MEMORY BB_NUMBER_THREADS PARALLEL_MAKE"
        export BB_PRESSURE_MAX_MEMORY="1000"
        export BB_NUMBER_THREADS="4"
        export PARALLEL_MAKE="-j 4"
        exec bash
    '
else
    ./docker/docker-run.sh bash -c '
        source poky/oe-init-build-env build-rpi3 > /dev/null
        # Memory pressure delays new tasks; single-task builds also avoid overlapping peaks.
        export BB_ENV_PASSTHROUGH_ADDITIONS="${BB_ENV_PASSTHROUGH_ADDITIONS:+$BB_ENV_PASSTHROUGH_ADDITIONS }BB_PRESSURE_MAX_MEMORY BB_NUMBER_THREADS PARALLEL_MAKE"
        export BB_PRESSURE_MAX_MEMORY="1000"
        export BB_NUMBER_THREADS="4"
        export PARALLEL_MAKE="-j 4"
        bitbake "$@"
    ' bash "$@"
fi
