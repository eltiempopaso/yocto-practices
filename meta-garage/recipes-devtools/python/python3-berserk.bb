SUMMARY = "Python client for the Lichess API"
HOMEPAGE = "https://github.com/rhgrant10/berserk"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5e0728c49429334992f78a833bff3e29"

SRC_URI = "git://github.com/rhgrant10/berserk.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "\
    python3-requests \
    python3-websocket-client \
    python3-deprecated \
"

