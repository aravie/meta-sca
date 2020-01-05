SUMMARY = "A minimalist production ready plugin system"
DESCRIPTION = "A minimalist production ready plugin system"
HOMEPAGE = "https://github.com/pytest-dev/pluggy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c8206d16fd5cc02fa9b0bb98955e5c2"

PYPI_PACKAGE = "pluggy"

DEPENDS += "${PYTHON_PN}-importlib-metadata-native"

inherit pypi
inherit native
inherit setuptools3

SRC_URI[md5sum] = "7f610e28b8b34487336b585a3dfb803d"
SRC_URI[sha256sum] = "15b2acde666561e1298d71b523007ed7364de07029219b604cf808bfa1c765b0"