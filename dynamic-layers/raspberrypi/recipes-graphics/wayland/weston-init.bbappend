# flutter-pi does not require wayland compositor
# because this flutter embedder directly uses DRM/KMS.
# So, weston is disabled by default for RPi.
SYSTEMD_AUTO_ENABLE:${PN} = "disable"
