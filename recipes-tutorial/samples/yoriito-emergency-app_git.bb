SUMMARY = "Yoriito Tutorial Flutter App"
DESCRIPTION = "A simple flutter app for Tutorial"
AUTHOR = "Yoriito"
HOMEPAGE = "https://github.com/yoriito/yoriito-tutorial"
BUGTRACKER = "None"
SECTION = "graphics"

require yoriito-tutorial.inc

SRC_URI += " \
  file://${FLUTTER_APPLICATION_INSTALL_SUFFIX}.service.in \
"

S = "${WORKDIR}/git"

PUBSPEC_APPNAME = "yoriito_emergency_app"
FLUTTER_APPLICATION_INSTALL_SUFFIX = "yoriito-emergency-app"
PUBSPEC_IGNORE_LOCKFILE = "1"
FLUTTER_APPLICATION_PATH = "ref_ui"

inherit flutter-app systemd

SYSTEMD_SERVICE:${PN} = "yoriito-emergency-app.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    for FLUTTER_RUNTIME_MODE in $FLUTTER_RUNTIME_MODES; do
        if ! echo "${FLUTTER_APP_RUNTIME_MODES}" | grep -qw "$FLUTTER_RUNTIME_MODE"; then
            bbnote "Skipping install for: ${FLUTTER_RUNTIME_MODE}"
            continue
        fi

        target_path=${D}${systemd_system_unitdir}/${FLUTTER_APPLICATION_INSTALL_SUFFIX}-${FLUTTER_RUNTIME_MODE}.service

        install -m 0644 ${WORKDIR}/${FLUTTER_APPLICATION_INSTALL_SUFFIX}.service.in \
            $target_path
        sed -i "s|%%FLUTTER_SDK_VERSION%%|${FLUTTER_SDK_VERSION}|g" \
            $target_path
        sed -i "s|%%FLUTTER_RUNTIME_MODE%%|${FLUTTER_RUNTIME_MODE}|g" \
            $target_path
    done
    # Create the represent service file
    install -m0644 $target_path ${D}${systemd_system_unitdir}/${FLUTTER_APPLICATION_INSTALL_SUFFIX}.service
}

FILES:${PN} += "${systemd_system_unitdir}"

RDEPENDS:${PN} += " yoriito-tutorial-app"
