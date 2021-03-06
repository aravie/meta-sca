SUMMARY = "A pep8 equivalent for bash scripts"
DESCRIPTION = "A pep8 equivalent for bash scripts"
HOMEPAGE = "http://docs.openstack.org/developer/bashate/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

DEPENDS += "${PYTHON_PN}-native \
            ${PYTHON_PN}-pbr-native \
            ${PYTHON_PN}-babel-native"

PYPI_PACKAGE = "bashate"

inherit pypi
inherit sca-sanity
inherit setuptools3

FILES_${PN} += "${datadir}"
SRC_URI += "file://bashate.sca.description"

inherit native

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/bashate.sca.description ${D}${datadir}
}

SRC_URI[md5sum] = "8143df66c83808ab25a59319da236fb1"
SRC_URI[sha256sum] = "eb990cf07c0ca09ee06f3b7eff79232a2d6eff9d2bdf141e8126efebb0521a17"
