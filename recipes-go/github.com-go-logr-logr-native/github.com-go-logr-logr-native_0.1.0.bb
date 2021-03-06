SUMMARY = "A simple logging interface for Go"
HOMEPAGE = "https://github.com/go-logr/logr"

SRC_URI = "git://${GO_IMPORT};protocol=https;tag=v${PV}"

GO_IMPORT = "github.com/go-logr/logr"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM  = "file://src/${GO_IMPORT}/LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

inherit go
inherit native

do_install_append() {
    # We don't need that
    rm -rf ${D}${bindir}/examples
}