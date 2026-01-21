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
IMAGE_INSTALL:append = " libgpiod" 
IMAGE_INSTALL:append = " i2c-tools" 
IMAGE_INSTALL:append = " libgpiod-tools" 
IMAGE_INSTALL:append = " dtc" 
IMAGE_INSTALL:append = " util-linux" 
IMAGE_INSTALL:append = " python3" 
#IMAGE_INSTALL:append = " python3-pip" 
IMAGE_INSTALL:append = " python3-kivy" 
IMAGE_INSTALL:append = " kivy-app-init" 
#IMAGE_INSTALL:append = " xinit" 
#IMAGE_INSTALL:append = " mesa" 
#IMAGE_INSTALL:append = " mesa-utils" 
IMAGE_INSTALL:append = " kmscube" 
IMAGE_INSTALL:append = " mtdev" 
IMAGE_INSTALL:append = " strace" 
#IMAGE_INSTALL:append = " xserver-xorg" 
IMAGE_INSTALL:append = " libdrm" 
IMAGE_INSTALL:append = " libsdl2" 
IMAGE_INSTALL:append = " libsdl2-image" 
IMAGE_INSTALL:append = " libsdl2-mixer" 
IMAGE_INSTALL:append = " libsdl2-ttf" 
IMAGE_INSTALL:append = " mesa" 
#IMAGE_INSTALL:append = " mesa-dri" 
IMAGE_INSTALL:append = " libgbm" 
IMAGE_INSTALL:append = " lichess-kivy-app" 


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

