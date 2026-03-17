SUMMARY = "Async library for DGT chess boards"
HOMEPAGE = "https://github.com/niklasf/python-asyncdgt"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/niklasf/python-asyncdgt.git;branch=master;protocol=https"
SRCREV = "acd6034dbd0a70c0807b84af49bf51d5cf822d14"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "\
    python3-asyncio \
    python3-pyserial \
"
