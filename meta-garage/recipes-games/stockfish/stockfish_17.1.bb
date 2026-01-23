DESCRIPTION = "Stockfish chess engine"
HOMEPAGE = "https://stockfishchess.org/"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://Copying.txt;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "https://github.com/official-stockfish/Stockfish/archive/refs/tags/sf_17.1.tar.gz"
SRC_URI[sha256sum] = "0cfd9396438798cc68f5c0d5fa0bb458bb8ffff7de06add841aaeace86bec1f1"

S = "${WORKDIR}/Stockfish-sf_17.1"

# Stockfish has no configure step
do_configure[noexec] = "1"

#
# Map Yocto architectures to Stockfish architectures
#
STOCKFISH_ARCH = "generic"

STOCKFISH_ARCH:armv7a   = "armv7"
STOCKFISH_ARCH:armv7ve  = "armv7"
STOCKFISH_ARCH:arm      = "armv7"
STOCKFISH_ARCH:aarch64  = "arm64"
STOCKFISH_ARCH:x86_64   = "x86-64"
STOCKFISH_ARCH:i686     = "x86-32"


TARGET_CXXFLAGS:remove = "-fcanon-prefix-map"
TARGET_CFLAGS:remove  = "-fcanon-prefix-map"
LDFLAGS:remove        = "-fcanon-prefix-map"

do_compile() {
    cd ${S}/src

    oe_runmake build \
        ARCH=${STOCKFISH_ARCH} \
        NO_NNUE_DOWNLOAD=1 \
        COMP=gcc \
        CXX="${CXX}" \
        CC="${CC}"
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/src/stockfish ${D}${bindir}/stockfish

    install -d ${D}${datadir}/stockfish
    install -m 0644 ${S}/src/*.nnue ${D}${datadir}/stockfish/
}



