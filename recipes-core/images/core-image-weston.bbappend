DESCRIPTION = "Yoriito Reference OS image"
LICENSE = "Apache-2.0"

YORIITO_VERSION = "0.0.1"

# Image Configuration
IMAGE_VERSION_SUFFIX = "${YORIITO_VERSION}"

# Package Installation
IMAGE_INSTALL:append = " \
    packagegroup-yoriito-core \
    packagegroup-yoriito-flutter \
    packagegroup-yoriito-tools-debug \
"

IMAGE_FEATURES:append = " \
    ssh-server-openssh \
"

# For MACHINE specific config
IMAGE_FSTYPES:raspberrypi4-64 = "tar.gz wic.bz2"

# For SDK config
SDK_VERSION = "${YORIITO_VERSION}"

TOOLCHAIN_HOST_TASK:append = " \
    nativesdk-packagegroup-yoriito-sdk-host \
"
TOOLCHAIN_TARGET_TASK:append = " \
    packagegroup-yoriito-sdk-target \
"
