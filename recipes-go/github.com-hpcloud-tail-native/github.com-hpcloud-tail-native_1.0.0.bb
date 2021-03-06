SUMMARY = "Go package for reading from continously updated files"
HOMEPAGE = "https://github.com/hpcloud/tail"

SRC_URI = "git://${GO_IMPORT};protocol=https;tag=v${PV}"
GO_IMPORT = "github.com/hpcloud/tail"

LICENSE = "MIT"
LIC_FILES_CHKSUM  = "file://src/${GO_IMPORT}/LICENSE.txt;md5=0bdce43b16cd5c587124d6f274632c87"

inherit go
inherit native