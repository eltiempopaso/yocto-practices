SUMMARY = "Lichess Kivy Chess App"
DESCRIPTION = "Pyside6 chess-station application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/urichess/chess-station.git;protocol=ssh;user=git;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit python3native useradd

CHESS_HOME = "${localstatedir}/lib/chess-station"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --create-home \
    --home-dir ${CHESS_HOME} \
    --user-group \
    --groups video,input \
    --shell /bin/false \
    chess"

RDEPENDS:${PN} += "\
    bash \
    python3-berserk \
    python3-chess \
    python3-core \
    python3-ndjson \
    python3-requests \
    python3-pyserial \
    python3-pyside6 \
"

APPDIR = "/opt/chess-station"

do_install() {
    # Copiar el repo tal cual
    install -d ${D}${APPDIR}
    cp -r ${S}/* ${D}${APPDIR}/

    # Launcher
    install -d ${D}${bindir}
    cat << 'EOF' > ${D}${bindir}/chess-station-app
#!/bin/sh
cd /opt/chess-station
exec python3 main.py "$@"
EOF
    chmod +x ${D}${bindir}/chess-station-app
}

do_install:append() {
    install -d -m 0750 -o chess -g chess ${D}${CHESS_HOME}
}

FILES:${PN} += "\
    ${APPDIR} \
    ${CHESS_HOME} \
    ${bindir}/chess-station-app\
"

