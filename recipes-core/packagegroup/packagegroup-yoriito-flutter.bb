SUMMARY = "Packagegroup for flutter"
LICENSE = "Apache-2.0"

# To override the default PACKAGE_ARCH, use inherit_defer to derive nativesdk
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit_defer packagegroup

PACKAGES = "${PN}"
RDEPENDS:${PN} = " \
    weston \
    weston-init \
    yoriito-emergency-app \
"
# for raspberrypi only
RDEPENDS:${PN}:append:raspberrypi4-64 = " \
    flutter-pi \
"
RDEPENDS:${PN}:remove:raspberrypi4-64 = " \
    weston \
    weston-init \
"
