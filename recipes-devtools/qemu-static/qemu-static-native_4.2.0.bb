SUMMARY = "Fast open source processor emulator (compiled statically)"
HOMEPAGE = "http://qemu.org"
LICENSE = "GPLv2 & LGPLv2.1"

# NOTE: If you get an configure error like
# ERROR: sizeof(size_t) doesn't match GLIB_SIZEOF_SIZE_T.
#        You probably need to set PKG_CONFIG_LIBDIR
#        to point to the right pkg-config files for your
#        build target
#
# WARNING: exit code 1 from a shell command.
#
# you likely need to install glib-2.0-dev on your host

SRC_URI = "https://download.qemu.org/qemu-${PV}.tar.xz"

SRC_URI[md5sum] = "278eeb294e4b497e79af7a57e660cb9a"
SRC_URI[sha256sum] = "d3481d4108ce211a053ef15be69af1bdd9dde1510fda80d92be0f6c3e98768f0"

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=8c5efda6cf1e1b03dcfd0e6c0d271c7f"

S = "${WORKDIR}/qemu-${PV}"
B = "${WORKDIR}/build"

DEPENDS = "glib-2.0-native zlib-native"

inherit pkgconfig
inherit native

QEMU_CONFIGURE_OPTS = "\
                       --prefix=${prefix} \
                       --bindir=${bindir} \
                       --includedir=${includedir} \
                       --libdir=${libdir} \
                       --mandir=${mandir} \
                       --datadir=${datadir} \
                       --docdir=${docdir}/${BPN} \
                       --sysconfdir=${sysconfdir} \
                       --libexecdir=${libexecdir} \
                       --localstatedir=${localstatedir} \
                       --with-confsuffix=/${BPN} \
                       --disable-strip \
                       --disable-werror \
                       --extra-cflags='${CFLAGS}' \
                       --static \
                       --disable-system \
                       --enable-linux-user \
                       --disable-tools \
                       --disable-blobs \
                       --disable-guest-agent \
                    "

QEMU_TARGETS = "arm-linux-user \
                aarch64-linux-user \
                i386-linux-user \
                mips-linux-user \
                mipsel-linux-user \
                mips64-linux-user \
                mips64el-linux-user \
                ppc-linux-user \
                riscv32-linux-user \
                riscv64-linux-user \
                sh4-linux-user \
                x86_64-linux-user"

QEMU_TARGET_CONF_OPT = "${@','.join(d.getVar('QEMU_TARGETS').split(' '))}"

do_configure() {
    ${S}/configure ${QEMU_CONFIGURE_OPTS} \    
    --target-list=${QEMU_TARGET_CONF_OPT}
}

do_compile() {
    oe_runmake
}

FILES_${PN} = "${bindir}"

do_install () {
	export STRIP=""
	oe_runmake 'DESTDIR=${D}' install

    ## This otherwise collides with qemu-native, as we only need the binaries, it's okay here
    rm -rf ${D}${datadir}

    ## Rename the binaries to *-static
    find ${D} -executable -type f -exec mv {} {}-static \;
}

INSANE_SKIP_${PN} = "arch"