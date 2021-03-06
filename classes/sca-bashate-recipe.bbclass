## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

inherit sca-bashate-core
inherit sca-global

inherit python3-dir

SCA_DEPLOY_TASK = "do_sca_deploy_bashate_recipe"

python do_sca_deploy_bashate_recipe() {
   sca_conv_deploy(d, "bashate", "txt")
}

addtask do_sca_bashate_core before do_install after do_compile
addtask do_sca_deploy_bashate_recipe before do_package after do_sca_bashate_core

do_sca_bashate_core[depends] += "${@oe.utils.conditional('SCA_FORCE_RUN', '1', '${PN}:do_sca_do_force_meta_task', '', d)}"
do_sca_deploy_bashate_recipe[depends] += "${@oe.utils.conditional('SCA_FORCE_RUN', '1', '${PN}:do_sca_do_force_meta_task', '', d)}"

DEPENDS += "sca-recipe-bashate-rules-native"