SUMMARY = "Lichess Kivy Chess App"
DESCRIPTION = "Kivy chess application (ui/ChessApp.py entrypoint)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/urichess/lichess-kivy-app.git;protocol=ssh;user=git;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit python3native

RDEPENDS:${PN} += "\
    python3-core \
    python3-kivy \
    python3-requests \
    python3-berserk \
    python3-ndjson \
    python3-chess \
    python3-pyserial \
    python3-pyperclip \
    python3-opencv \
"

APPDIR = "/opt/lichess-kivy-app"

do_install() {
    # Copiar el repo tal cual
    install -d ${D}${APPDIR}
    cp -r ${S}/* ${D}${APPDIR}/

    # Launcher
    install -d ${D}${bindir}
    cat << 'EOF' > ${D}${bindir}/chess-app
#!/bin/sh
cd /opt/lichess-kivy-app
exec python3 -m ui.ChessApp "$@"
EOF
    chmod +x ${D}${bindir}/chess-app
}

FILES:${PN} += "\
    ${APPDIR} \
    ${bindir}/chess-app \
"


