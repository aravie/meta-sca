SUMMARY = "C parser in Python."
DESCRIPTION = "C parser in Python."
HOMEPAGE = "https://github.com/eliben/pycparser"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86f1cedb4e6410a88ce8e30b91079169"

RDEPENDS_${PN} += "${PYTHON_PN}-native"

PYPI_PACKAGE = "pycparser"

inherit pypi
inherit setuptools3
inherit native

SRC_URI[md5sum] = "b8f88de737db8c346ee8d31c07c7a25a"
SRC_URI[sha256sum] = "2d475327684562c3a96cc71adf7dc8c4f0565175cf86b6d7a404ff4c771f15f0"
