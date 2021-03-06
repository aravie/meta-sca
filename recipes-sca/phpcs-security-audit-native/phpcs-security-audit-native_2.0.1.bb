SUMMARY = "phpcs-security-audit is a set of PHP_CodeSniffer rules that finds vulnerabilities and weaknesses related to security in PHP code"

HOMEPAGE = "https://github.com/FloeDesignTechnologies/phpcs-security-audit"

SRC_URI = " git://github.com/FloeDesignTechnologies/phpcs-security-audit.git;protocol=https;nobranch=1;tag=${PV} \
            file://phpsecaudit.sca.description"

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=412af50a7c6ed96fe188e6672d9f3d9b"

S = "${WORKDIR}/git"

DEPENDS += "phpcodesniffer-native"

PHPCOMPOSER_PKGS_NAME = "pheromone/phpcs-security-audit=${PV}"

inherit native
inherit phpcomposer
inherit sca-sanity

FILES_${PN} = "${bindir} ${datadir}"

do_compile_prepend() {
    rm -f ${S}/composer.json ${S}/composer.lock
}

do_install_append() {
    ## We need to move the ruleset so it gets recognized by phpcodesniffer
    mv ${D}${bindir}/phpcs-security-audit/vendor/pheromone/phpcs-security-audit/Security \
        ${D}${bindir}/phpcs-security-audit/vendor/squizlabs/php_codesniffer/src/Standards/
    mkdir -p ${D}${datadir}
    install ${WORKDIR}/phpsecaudit.sca.description ${D}${datadir}/
}

