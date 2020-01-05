SUMMARY = "Check for python builtins being used as variables or parameters"
DESCRIPTION = "Check for python builtins being used as variables or parameters"
HOMEPAGE = "https://github.com/gforcada/flake8-builtins"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PYPI_PACKAGE = "flake8-builtins"

DEPENDS += " \
            ${PYTHON_PN}-flake8-native \
            ${PYTHON_PN}-mccabe-native \
            ${PYTHON_PN}-pycodestyle-native \
            ${PYTHON_PN}-pyflakes-native \
            "

inherit pypi
inherit native
inherit setuptools3

SRC_URI[md5sum] = "b7c7d3c26ac2a22e81407a689f78a44d"
SRC_URI[sha256sum] = "cd7b1b7fec4905386a3643b59f9ca8e305768da14a49a7efb31fe9364f33cd04"