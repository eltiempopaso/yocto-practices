DESCRIPTION = "Create /data mountpoint"
LICENSE = "MIT"

do_install() {
    install -d ${D}/data
}

FILES:${PN} = "/data"
