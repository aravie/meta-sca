# This a backport copy of upstream poky class of the same name
PYTHON_BASEVERSION = "3.5"
PYTHON_ABI = "m"
PYTHON_DIR = "python${PYTHON_BASEVERSION}"
PYTHON_PN = "python3"
PYTHON_SITEPACKAGES_DIR = "${libdir}/${PYTHON_DIR}/site-packages"