# meta-yoriito-tutorial

This layer provides the demo & tutorial components from the Yoriito project for integration with the Yocto Project.

## Overview

meta-yoriito-tutorial is a Yocto layer that provides recipes and configurations
to build and integrate software components developed by the Yoriito project,
including application, libraries, and tools.

The current supported MACHINE are like the following:
- `raspberrypi4-64`

## Getting Started

### Build OS image/Yocto SDK

This layer provides the reference image based on `core-image-weston`.
To build the image, you can use the following commands:

```bash
# Get repo
mkdir -p ~/.local/bin
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/.local/bin/repo
chmod a+x ~/.local/bin/repo
export PATH=$PATH:~/.local/bin

# Fetch the layers
repo init -u https://github.com/yoriito/yoriito.git
repo sync

# Configure the build environment
source poky/oe-init-build-env
bitbake-layers add-layer \
  ../meta-openembedded/meta-oe \
  ../meta-openembedded/meta-python \
  ../meta-openembedded/meta-networking \
  ../meta-openembedded/meta-filesystems \
  ../meta-rust-bin \
  ../meta-flutter \
  ../meta-yoriito \
  ../meta-yoriito-tutorial \
  ../meta-raspberrypi

# Configure the build for required features
echo "DISTRO_FEATURES:append = \" systemd usrmerge opengl wayland pam\"" >> conf/local.conf
echo "VIRTUAL-RUNTIME_init_manager = \"systemd\"" >> conf/local.conf
echo "VIRTUAL-RUNTIME_udev = \"systemd\"" >> conf/local.conf

# Machine specific configurations
echo "MACHINE = \"raspberrypi4-64\"" >> conf/local.conf
echo "ENABLE_UART = \"1\"" >> conf/local.conf
echo "MACHINE_EXTRA_RRECOMMENDS:remove = \"linux-firmware-rpidistro-bcm43455 linux-firmware-rpidistro-bcm43456\"" >> conf/local.conf

# Build the image
bitbake core-image-weston

# Build the SDK
bitbake core-image-weston -c populate_sdk
```

### Connecting to the PAL

This OS image does not include a PAL by default.

So, you need to prepare a PAL connection to kura.

Yoriito provides sample PAL applications in the following repository:

- [Yoriito Tutorial - PAL](https://github.com/yoriito/yoriito-tutorial/ref_pal)
- [Yoriito Pocket PAL](https://github.com/yoriito/pocket-pal)

If you don't create your own PAL, please use one of the above repositories.

Since there are the usage guides of them in their repositories, please refer to them.

## Dependencies

This layer depends on:

- URI: https://git.yoctoproject.org/poky
  - branch: scarthgap
    - layers: meta
- URI: https://git.openembedded.org/meta-openembedded
  - branch: scarthgap
    - layers: meta-oe
- URI: https://github.com/meta-flutter/meta-flutter
  - branch: scarthgap
    - layers: meta-flutter
- URI: https://github.com/yoriito/meta-yoriito
  - branch: main
    - layers: meta-yoriito

### For Raspberry Pi

- URI: git://git.yoctoproject.org/meta-raspberrypi
  - branch: scarthgap
    - layers: meta-raspberrypi

## Supported Versions

- Yocto Project: 5.0 (scarthgap)

## License

This layer is distributed under the Apache License 2.0.
Individual recipes and software components are subject to their respective licenses.
