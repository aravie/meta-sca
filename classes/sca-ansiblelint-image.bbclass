## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-helper
inherit sca-global
inherit sca-ansiblelint-core

SCA_DEPLOY_TASK = "do_sca_deploy_ansiblelint_image"

python do_sca_deploy_ansiblelint_image() {
    sca_conv_deploy(d, "ansiblelint", "txt")
}

addtask do_sca_ansiblelint_core before do_image_complete after do_image
addtask do_sca_deploy_ansiblelint_image before do_image_complete after do_sca_ansiblelint_core

do_sca_ansiblelint_core[depends] += "${@oe.utils.conditional('SCA_FORCE_RUN', '1', '${PN}:do_sca_do_force_meta_task', '', d)}"
do_sca_deploy_ansiblelint_image[depends] += "${@oe.utils.conditional('SCA_FORCE_RUN', '1', '${PN}:do_sca_do_force_meta_task', '', d)}"

DEPENDS += "sca-image-ansiblelint-rules-native"
