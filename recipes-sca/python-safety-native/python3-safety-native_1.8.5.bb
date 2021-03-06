SUMMARY = "Safety checks your installed dependencies for known security vulnerabilities"
DESCRIPTION = "Safety checks your installed dependencies for known security vulnerabilities"
HOMEPAGE = "https://github.com/pyupio/safety"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=558baaefeb02113f2331ea2fd536fa86"

SRC_URI += "file://safety.sca.description"

DEPENDS += "\
            ${PYTHON_PN}-click-native \
            ${PYTHON_PN}-requests-native \
            ${PYTHON_PN}-packaging-native \
            ${PYTHON_PN}-dparse-native \
            "

PYPI_PACKAGE = "safety"

FILES_${PN} += "${datadir}"

inherit pypi
inherit native
inherit sca-sanity
inherit setuptools3

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/safety.sca.description ${D}${datadir}
}

SRC_URI[md5sum] = "125e0a81b429acfcf0e4a6bcede8775b"
SRC_URI[sha256sum] = "5059f3ffab3648330548ea9c7403405bbfaf085b11235770825d14c58f24cb78"
