SUMMARY = "URI custom image"
DESCRIPTION = "Custom image for Raspberry Pi 2 with our packages"
LICENSE = "MIT"

# Start from a known base image
inherit core-image

# Base features (very important)
IMAGE_FEATURES += "ssh-server-dropbear"

# Packages to install
IMAGE_INSTALL += " \
    hello \
    hello-proc \
    kernel-modules \
"

# Optional but very common
IMAGE_LINGUAS = "en-us"

