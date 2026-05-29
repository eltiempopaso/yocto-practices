ENABLE_I2C = "1"

RPI_EXTRA_CONFIG:append = "\ndtoverlay=nunchuk\n"

#KERNEL_MODULE_AUTOLOAD += " nunchuk"

# =====================================================================

# TRAZAS DE DEPURACIÓN (Borrar o comentar una vez funcione)

# =====================================================================

python () {

    bb.plain("==================================================")

    bb.plain("  [MI CAPA] Cargando meta-garage raspberrypi3-64.conf")

    bb.plain("  ENABLE_I2C actual: %s" % d.getVar('ENABLE_I2C'))

    bb.plain("  RPI_EXTRA_CONFIG actual: %s" % d.getVar('RPI_EXTRA_CONFIG'))

    bb.plain("==================================================")

} 


