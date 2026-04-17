SUMMARY = "Packagegroup for Yoriito tools for debugging"
LICENSE = "Apache-2.0"

inherit packagegroup

PACKAGES = "${PN}"
RDEPENDS:${PN} = " \
    gdb \
    weston-examples \
"
