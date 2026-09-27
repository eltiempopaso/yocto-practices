SUMMARY = "Python @deprecated decorator"
HOMEPAGE = "https://github.com/tantale/deprecated"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=44288e26f4896bdab14072d4fa35ff01"

SRC_URI = "git://github.com/tantale/deprecated.git;protocol=https;branch=master"
SRCREV = "d135459ef6c1fdd005f28c6e2cf7915e8fb8d0e1"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "\
    python3-wrapt \
"

