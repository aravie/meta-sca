SUMMARY = "Polyfill package for Flake8 plugins"
HOMEPAGE = "https://gitlab.com/pycqa/flake8-polyfill"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4638d28f0c4a8f3900ce3e309d204b73"

PYPI_PACKAGE = "flake8-polyfill"

DEPENDS += "${PYTHON_PN}-flake8-native"

inherit pypi
inherit native
inherit setuptools3

SRC_URI[md5sum] = "076110bed47814d27019a0db25ad481b"
SRC_URI[sha256sum] = "e44b087597f6da52ec6393a709e7108b2905317d0c0b744cdca6208e670d8eda"
