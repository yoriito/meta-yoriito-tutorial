SUMMARY = "Packagegroup for Yoriito SDK target"
LICENSE = "Apache-2.0"

# To override the default PACKAGE_ARCH, use inherit_defer to derive packagegroup
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit_defer packagegroup

PACKAGES = "${PN}"
RDEPENDS:${PN} = " \
  grpc \
  protobuf \
  grpc-dev \
  protobuf-dev \
  protobuf-staticdev \
"
