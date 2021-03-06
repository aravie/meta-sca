SUMMARY = "Analyzer of PHP code to search issues with deprecated functionality in newer interpreter versions."

HOMEPAGE = "https://github.com/wapmorgan/PhpCodeFixer"

SRC_URI = " git://github.com/wapmorgan/PhpCodeFixer.git;protocol=https;tag=${PV} \
            file://phpcodefixer.sca.description"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c98596cdc7ad2b3b9a3a84255a83cb9f"

S = "${WORKDIR}/git"

PHPCOMPOSER_PKGS_NAME = "wapmorgan/php-code-fixer:${PV}"

inherit native
inherit phpcomposer
inherit sca-sanity

FILES_${PN} = "${bindir} ${datadir}"

do_compile_prepend() {
    rm -f ${S}/composer.json ${S}/composer.lock
}

do_install_append() {
    mkdir -p ${D}${datadir}
    install ${WORKDIR}/phpcodefixer.sca.description ${D}${datadir}/
}

