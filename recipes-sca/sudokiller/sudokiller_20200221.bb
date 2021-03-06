SUMMARY = "A tool to identify and exploit sudo rules' misconfigurations and vulnerabilities within sudo"
HOMEPAGE = "https://github.com/TH3xACE/SUDO_KILLER"

SRC_URI = " git://github.com/TH3xACE/SUDO_KILLER.git;protocol=https \
            file://sudokiller.sca.description"
SRCREV = "9f612e369dbd3c3539a7f13fffce994abe663670"
UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f45999e825d6792e32a1cbadd968b1b7"

inherit sca-sanity

RDEPENDS_${PN} += "bash"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${bindir}/sudokiller
    cp -r ${S}/* ${D}${bindir}/sudokiller/
    # Rename main script to strip the version
    mv ${D}${bindir}/sudokiller/SUDO_KILLERv2*.sh ${D}${bindir}/sudokiller/sudokiller
    chmod +x ${D}${bindir}/sudokiller/sudokiller
    chown -R root:root ${D}${bindir}/sudokiller/
}

do_install_append_class-native () {
	install -d ${D}/${datadir}
    install ${WORKDIR}/sudokiller.sca.description ${D}${datadir}
}

FILES_${PN} = "${bindir}"
FILES_${PN}_class-native += "${datadir}"

# We don't really care about debug package for this one
# also because of the issue mentioned below
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Package contains exploit sample from (maybe) not matching arch
# plus all the dependencies from that arch
# Ignore that while packaging
INSANE_SKIP_${PN} += "file-rdeps arch"

BBCLASSEXTEND = "native"