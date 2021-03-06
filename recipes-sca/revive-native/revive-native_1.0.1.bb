SUMMARY = "faster, stricter, configurable, extensible, and beautiful drop-in replacement for golint"
HOMEPAGE = "https://github.com/mgechev/revive"

SRC_URI = "file://revive.sca.description \
           git://${GO_IMPORT};protocol=https;name=revive;tag=v${PV}"

GO_IMPORT = "github.com/mgechev/revive"

LICENSE = "MIT"
LIC_FILES_CHKSUM  = "file://src/${GO_IMPORT}/LICENSE;md5=6898b1ee52bce3bd296aba3dff3fffb0"

inherit go
inherit native
inherit sca-sanity

FILES_${PN} += "${datadir}"

DEPENDS += "\
            github.com-burntsushi-toml-native \
            github.com-blang-semver-native \
            github.com-fatih-color-native \
            github.com-fatih-structtag-native \
            github.com-mgechev-dots-native \
            github.com-mitchellh-go-homedir-native \
            github.com-olekukonko-tablewriter-native \
            github.com-pkg-errors-native \
            golang.org-x-tools-native \
            k8s.io-klog-native \
           "

do_install_append() {
    install -d ${D}${datadir}
    install ${WORKDIR}/revive.sca.description ${D}${datadir}/
}

