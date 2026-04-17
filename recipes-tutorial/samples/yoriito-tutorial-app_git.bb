SUMMARY = "Yoriito Vehicle API Reference Headless Application"
DESCRIPTION = "A sample Headless app for Tutorial. This recipe does not include PAL."
AUTHOR = "Yoriito"
HOMEPAGE = "https://github.com/yoriito/yoriito-tutorial"
BUGTRACKER = "None"
SECTION = "apps"
DEPENDS = " \
    protobuf \
    protobuf-native \
    grpc \
    grpc-native \
    yoriito-common-library \
    yoriito-signal-api \
"
require yoriito-tutorial.inc

SRC_URI += " \
    file://sample-app.service \
"
S = "${WORKDIR}/git"

inherit cmake systemd

EXTRA_OECMAKE = " \
    -DCMAKE_CXX_FLAGS='-I${STAGING_INCDIR}/yoriito' \
"
TARGET_LDFLAGS += " \
    -L${STAGING_LIBDIR}/yoriito \
"

SYSTEMD_SERVICE:${PN} = "sample-app.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

do_configure:prepend() {
    # generate gRPC header
    ${S}/ref_proto/convert_grpc.sh
}

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}/
        install -m 0644 ${WORKDIR}/sample-app.service ${D}${systemd_system_unitdir}/
    fi
}

FILES:${PN} += " ${datadir} ${systemd_system_unitdir}"
