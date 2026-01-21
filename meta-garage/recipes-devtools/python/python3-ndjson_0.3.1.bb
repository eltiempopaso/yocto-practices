SUMMARY = "ndjson — newline-delimited JSON (NDJSON) support"
HOMEPAGE = "https://github.com/rhgrant10/ndjson"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a47e3dfed038aec4753d29be5c5ba5f1"

SRC_URI = "https://files.pythonhosted.org/packages/source/n/ndjson/ndjson-0.3.1.tar.gz"

SRC_URI[md5sum] = "631b36ea929a48c00fd00e1b1f6162eb"
SRC_URI[sha256sum] = "bf9746cb6bb1cb53d172cda7f154c07c786d665ff28341e4e689b796b229e5d6"

SRC_URI += "file://disable-test.patch"
#DISTUTILS_SETUP_ARGS += "--disable-pytest"

inherit pypi setuptools3

RDEPENDS:${PN} = ""

