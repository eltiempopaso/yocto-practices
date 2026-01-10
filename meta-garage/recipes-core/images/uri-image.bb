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
    hello-version \
    kernel-modules \
"

#IMAGE_INSTALL:append = " \
#    python3 \
#    python3-core \
#    python3-logging \
#    python3-multiprocessing \
#    python3-threading \
#    python3-ctypes \
#    python3-setuptools \
#"

#IMAGE_INSTALL:append = " \
#    python3-kivy \
#"

#IMAGE_INSTALL:append = " \
#    mesa \
#    mesa-megadriver \
#    libdrm \
#    fbset \
#"

# Optional but very common
IMAGE_LINGUAS = "en-us"

