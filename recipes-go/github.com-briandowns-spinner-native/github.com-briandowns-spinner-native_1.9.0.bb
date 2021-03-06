SUMMARY = "Go (golang) package with 70+ configurable terminal spinner/progress indicators"
HOMEPAGE = "https://github.com/briandowns/spinner"

SRC_URI = "git://${GO_IMPORT};protocol=https;tag=v${PV}"
GO_IMPORT = "github.com/briandowns/spinner"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=5335066555b14d832335aa4660d6c376"

DEPENDS += "\
            github.com-fatih-color-native \
            "

inherit go
inherit native