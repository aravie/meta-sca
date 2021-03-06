## SPDX-License-Identifier: BSD-2-Clause
## Copyright (c) 2019, Konrad Weihmann

SCA_STANDARD_EXTRA_SUPPRESS ?= ""
SCA_STANDARD_EXTRA_FATAL ?= ""
SCA_STANDARD_FILE_FILTER ?= ".js .jsx"

inherit sca-conv-to-export
inherit sca-datamodel
inherit sca-global
inherit sca-helper
inherit sca-license-filter
inherit sca-suppress

DEPENDS += "standard-native"

def do_sca_conv_standard(d):
    import os
    import re
    
    package_name = d.getVar("PN")
    buildpath = d.getVar("SCA_SOURCES_DIR")

    pattern = r"^(?P<file>.*):(?P<line>\d+):(?P<col>\d+):\s+(?P<msg>.*)\s+\((?P<id>.*)\)"
    pattern_parser = r"^(?P<file>.*?):(?P<line>\d+):(?P<col>\d+):\s+Parsing error:\s+(?P<msg>.*)\s+\(.*"

    _suppress = sca_suppress_init(d)
    _findings = []

    if os.path.exists(d.getVar("SCA_RAW_RESULT_FILE")):
        with open(d.getVar("SCA_RAW_RESULT_FILE"), "r") as f:
            content = f.read()
            for m in re.finditer(pattern, content, re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            File=m.group("file"),
                                            BuildPath=buildpath,
                                            Column=m.group("col"),
                                            Tool="standard",
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID=m.group("id"),
                                            Severity="warning")
                    if g.ID == "null":
                        # this is a parser error, which is catched by
                        # the pattern below
                        continue
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings.append(g)
                except Exception as exp:
                    bb.warn(str(exp))
            for m in re.finditer(pattern_parser, content, re.MULTILINE):
                try:
                    g = sca_get_model_class(d,
                                            PackageName=package_name,
                                            File=m.group("file").strip(),
                                            BuildPath=buildpath,
                                            Column=m.group("col"),
                                            Tool="standard",
                                            Line=m.group("line"),
                                            Message=m.group("msg"),
                                            ID="parser_error",
                                            Severity="error")
                    if _suppress.Suppressed(g):
                        continue
                    if g.Scope not in clean_split(d, "SCA_SCOPE_FILTER"):
                        continue
                    if g.Severity in sca_allowed_warning_level(d):
                        _findings.append(g)
                except Exception as exp:
                    bb.warn(str(exp))

    sca_add_model_class_list(d, _findings)
    return sca_save_model_to_string(d)

python do_sca_standard_core() {
    import os
    import subprocess

    d.setVar("SCA_EXTRA_SUPPRESS", d.getVar("SCA_STANDARD_EXTRA_SUPPRESS"))
    d.setVar("SCA_EXTRA_FATAL", d.getVar("SCA_STANDARD_EXTRA_FATAL"))
    d.setVar("SCA_SUPRESS_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "standard-{}-suppress".format(d.getVar("SCA_MODE"))))
    d.setVar("SCA_FATAL_FILE", os.path.join(d.getVar("STAGING_DATADIR_NATIVE"), "standard-{}-fatal".format(d.getVar("SCA_MODE"))))

    os.environ["HOME"] = d.getVar("T")
    _args = ["standard"]
    _args += ["--verbose"]

    _files = get_files_by_extention(d, d.getVar("SCA_SOURCES_DIR"), d.getVar("SCA_STANDARD_FILE_FILTER"), \
                                    sca_filter_files(d, d.getVar("SCA_SOURCES_DIR"), clean_split(d, "SCA_FILE_FILTER_EXTRA")))
    cmd_output = ""
    if any(_files):
        _args += _files
        try:
            cmd_output = subprocess.check_output(_args, universal_newlines=True, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            cmd_output = e.stdout or ""

    result_raw_file = os.path.join(d.getVar("T"), "sca_raw_standard.txt")
    d.setVar("SCA_RAW_RESULT_FILE", result_raw_file)
    with open(result_raw_file, "w") as o:
        o.write(cmd_output)

    ## Create data model
    d.setVar("SCA_DATAMODEL_STORAGE", "{}/standard.dm".format(d.getVar("T")))
    dm_output = do_sca_conv_standard(d)
    with open(d.getVar("SCA_DATAMODEL_STORAGE"), "w") as o:
        o.write(dm_output)

    sca_task_aftermath(d, "standard", get_fatal_entries(d))
}
