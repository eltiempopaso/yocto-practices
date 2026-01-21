SUMMARY = "Python @deprecated decorator"
HOMEPAGE = "https://github.com/tantale/deprecated"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=44288e26f4896bdab14072d4fa35ff01"

SRC_URI = "git://github.com/tantale/deprecated.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "\
    python3-wrapt \
"

