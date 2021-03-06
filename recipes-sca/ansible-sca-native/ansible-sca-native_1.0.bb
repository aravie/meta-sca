SUMMARY = "Ansible playbooks"

SRC_URI = "file://ansible.sca.description \
           file://etc_security.yaml \
           file://cron_security.yaml \
           file://modprobe.yaml \
           file://passwd_security.yaml \
           file://sshd.yaml \
           file://sysconf_net.yaml \
           file://sysconf.yaml \
           "

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${SCA_LAYERDIR}/LICENSE;md5=a4a2bbea1db029f21b3a328c7a059172"

inherit native
inherit sca-sanity

B = "${WORKDIR}"

DEPENDS += "python3-ansible-native"

FILES_${PN} = "${datadir}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    mkdir -p ${D}${datadir}/ansible_sec
    for item in $(find ${B} -name "*.yaml"); do
        install -m 0644 ${item} ${D}${datadir}/ansible_sec/$(basename ${item})
    done
    install ${WORKDIR}/ansible.sca.description ${D}${datadir}/
}

