SUMMARY = "Packagegroup for Yoriito SDK host tools"
LICENSE = "Apache-2.0"

# To override the default PACKAGE_ARCH, use inherit_defer to derive nativesdk
inherit_defer packagegroup
inherit_defer nativesdk

PACKAGE_ARCH = "${SDK_ARCH}"

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

PACKAGES = "${PN}"
RDEPENDS:${PN} = " \
  nativesdk-make \
  nativesdk-packagegroup-sdk-host \
  nativesdk-protobuf-compiler \
  nativesdk-grpc-compiler \
"
