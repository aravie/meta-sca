SUMMARY = "The pytest framework makes it easy to write small tests, yet scales to support complex functional testing"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=81eb9f71d006c6b268cf4388e3c98f7b"

PYPI_PACKAGE = "pytest"

DEPENDS += " \
            ${PYTHON_PN}-atomicwrites-native \
            ${PYTHON_PN}-attrs-native \
            ${PYTHON_PN}-colorama-native \
            ${PYTHON_PN}-importlib-metadata-native \
            ${PYTHON_PN}-more-itertools-native \
            ${PYTHON_PN}-packaging-native \
            ${PYTHON_PN}-pathlib2-native \
            ${PYTHON_PN}-pluggy-native \
            ${PYTHON_PN}-py-native \
            ${PYTHON_PN}-wcwidth-native \
           "

inherit pypi
inherit native
inherit setuptools3

SRC_URI[md5sum] = "f0d3c0e6f71cfe53e24c736c4ceb7725"
SRC_URI[sha256sum] = "84dde37075b8805f3d1f392cc47e38a0e59518fb46a431cfdaf7cf1ce805f970"
