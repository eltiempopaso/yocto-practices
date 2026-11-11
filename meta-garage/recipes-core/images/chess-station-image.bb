SUMMARY = "Chess-Station custom image"
DESCRIPTION = "Custom image for Raspberry Pi 3b running the chess-station application"
LICENSE = "MIT"

# Start from a known base image
inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL:append = " chess-station-app" 
IMAGE_INSTALL:append = " chess-station-app-init"
IMAGE_INSTALL:append = " chrony chronyc"
IMAGE_INSTALL:append = " dhcp-dns-fallback"
IMAGE_INSTALL:append = " mesa" 
#IMAGE_INSTALL:append = " mesa-utils" 
IMAGE_INSTALL:append = " libdrm" 
IMAGE_INSTALL:append = " python3" 
#IMAGE_INSTALL:append = " qtbase-plugins"
IMAGE_INSTALL:append = " rootfs-switch"
IMAGE_INSTALL:append = " stockfish" 
IMAGE_INSTALL:append = " strace" 
IMAGE_INSTALL:append = " ttf-dejavu-sans"
IMAGE_INSTALL:append = " u-boot-fw-utils"

WKS_FILE  = "sdimage-ab.wks"
WKS_FILES = "sdimage-ab.wks"
