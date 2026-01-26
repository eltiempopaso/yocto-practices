DESCRIPTION = "Stockfish chess engine"
HOMEPAGE = "https://stockfishchess.org/"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://Copying.txt;md5=1ebbd3e34237af26da5dc08a4e440464"

# Fuentes
SRC_URI = "\
    https://github.com/official-stockfish/Stockfish/archive/refs/tags/sf_17.1.tar.gz;name=src \
    https://tests.stockfishchess.org/api/nn/nn-37f18f62d772.nnue;name=nnue1 \
    https://tests.stockfishchess.org/api/nn/nn-1c0000000000.nnue;name=nnue2 \
"

# Checksums de las fuentes
SRC_URI[src.sha256sum] = "0cfd9396438798cc68f5c0d5fa0bb458bb8ffff7de06add841aaeace86bec1f1"
SRC_URI[nnue1.sha256sum] = "37f18f62d772f3107e1d6aaca3898c130c3c86f2ab63e6555fbbca20635a899d"
SRC_URI[nnue2.sha256sum] = "1c0000000000a67d629999d932d0c373f7450ce43cd12d0562868f4eaf9ae2ad"

S = "${WORKDIR}/Stockfish-sf_17.1"

do_configure[noexec] = "1"

# Map Yocto arch → Stockfish arch
STOCKFISH_ARCH = "generic"

STOCKFISH_ARCH:armv7a   = "armv7"
STOCKFISH_ARCH:armv7ve  = "armv7"
STOCKFISH_ARCH:arm      = "armv7"
STOCKFISH_ARCH:aarch64  = "armv8"
STOCKFISH_ARCH:x86_64   = "x86-64"
STOCKFISH_ARCH:i686     = "x86-32"


TARGET_CXXFLAGS:remove = "-fcanon-prefix-map"
TARGET_CFLAGS:remove  = "-fcanon-prefix-map"
LDFLAGS:remove        = "-fcanon-prefix-map"

do_compile() {
    cd ${S}/src

    # Copiamos la red al sitio que espera Stockfish
    cp ${WORKDIR}/*.nnue .

    oe_runmake build \
        ARCH=${STOCKFISH_ARCH} \
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



