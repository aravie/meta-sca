SUMMARY = "Advanced oelint"
DESCRIPTION = "Based on the OpenEmbedded Styleguide and work done by oe-stylize-tool\n \
               this module offers a (nearly) complete linter for bitbake-recipes."
HOMEPAGE = "https://github.com/priv-kweihmann/oelint-adv"

SRC_URI = "git://github.com/priv-kweihmann/oelint-adv.git;protocol=https;branch=master;tag=${PV} \
           file://oelint.sca.description"

S = "${WORKDIR}/git"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e926c89aceef6c1a4247d5df08f94533"

DEPENDS += "\
            python3-anytree-native \
            python3-colorama-native \
            python3-urllib3-native \
            "

inherit native
inherit sca-sanity
inherit setuptools3

FILES_${PN} += "${datadir}"

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/oelint.sca.description ${D}${datadir}
}

