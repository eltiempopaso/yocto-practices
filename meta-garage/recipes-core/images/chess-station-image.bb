SUMMARY = "Chess-Station custom image"
DESCRIPTION = "Custom image for Raspberry Pi 2 with our packages"
LICENSE = "MIT"

# Start from a known base image
inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL:append = " python3" 

IMAGE_INSTALL:append = " mesa" 
IMAGE_INSTALL:append = " libdrm" 
#IMAGE_INSTALL:append = " mesa-utils" 

IMAGE_INSTALL:append = " strace" 

IMAGE_INSTALL:append = " stockfish" 
IMAGE_INSTALL:append = " u-boot-fw-utils"
IMAGE_INSTALL:append = " rootfs-switch"
#IMAGE_INSTALL:append = " qtbase-plugins"



IMAGE_INSTALL:append = " chess-station-app" 


WKS_FILE  = "sdimage-ab.wks"
WKS_FILES = "sdimage-ab.wks"

