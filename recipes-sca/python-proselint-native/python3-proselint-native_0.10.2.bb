SUMMARY = "A linter for prose."
DESCRIPTION = "A linter for prose."
HOMEPAGE = "https://github.com/amperser/proselint/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=64a578e1ca1f25248a8d50f32fdf14e7"

DEPENDS += "${PYTHON_PN}-native \
            ${PYTHON_PN}-future-native \
            ${PYTHON_PN}-six-native \
            ${PYTHON_PN}-click-native"

PYPI_PACKAGE = "proselint"

inherit pypi
inherit sca-sanity
inherit setuptools3

FILES_${PN} += "${datadir}"
SRC_URI += "file://proselint.sca.description"

inherit native

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/proselint.sca.description ${D}${datadir}
}

SRC_URI[md5sum] = "68b800b5df2b98b2759087749eb88660"
SRC_URI[sha256sum] = "3a87eb393056d1bc77d898e4bcf8998f50e9ad84f7b9ff7cf2720509ac8ef904"
