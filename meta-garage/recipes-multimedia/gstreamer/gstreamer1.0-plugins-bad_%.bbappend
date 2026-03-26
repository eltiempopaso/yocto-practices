# Habilitar soporte KMS/DRM (kmsgrab, kmssink, etc.)
PACKAGECONFIG:append = " kms"

# Asegurar dependencia explícita de libdrm (normalmente ya viene, pero mejor garantizarlo)
DEPENDS:append = " libdrm"

# (Opcional pero recomendable) asegurar que el paquete runtime correcto se genere
RDEPENDS:${PN}:append = " libdrm"

# Debug opcional: ver qué PACKAGECONFIG queda activo en build
# (solo útil si estás depurando, puedes comentar luego)
# python () {
#     bb.note("GStreamer plugins-bad PACKAGECONFIG: %s" % d.getVar('PACKAGECONFIG'))
# }
