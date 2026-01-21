SUMMARY = "Python chess library (move generation, PGN, tablebases, UCI)"
DESCRIPTION = "python-chess is a pure Python chess library with support for move generation, validation, UCI/XBoard engines, PGN parsing and various endgame tablebases."
HOMEPAGE = "https://github.com/niklasf/python-chess"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"

PYPI_NAME = "chess"
SRC_URI[pyPI] = "python-chess/${PYPI_NAME}"
SRC_URI[sha256sum] = "a8b43e5678fdb3000695bdaa573117ad683761e5ca38e591c4826eba6d25bb39"

inherit pypi setuptools3

# For python3 support
RDEPENDS_${PN} = "python3"

# Optional: if you want the library installed as a module
FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/chess*"



