# yocto-practicescp tmp/deploy/images/raspberrypi2/zImage /srv/tftp/zImage
 cp tmp/deploy/images/raspberrypi2/bcm2709-rpi-2-b.dtb /srv/tftp/bcm2709-rpi-2-b.dtb 
sudo tar -xpf  ./tmp/work/raspberrypi2-poky-linux-gnueabi/uri-image/1.0/deploy-uri-image-image-complete/uri-image-raspberrypi2.rootfs.tar.bz2 -C /nfs

kernel developer
-------------------------

git clone --depth 1 xxx

git rev-parse --is-shallow-repository
git fetch --unshallow


git fetch --depth 1000
git fetch --depth 5000
git fetch --unshallow


git log --oneline | wc -l


---------------

sudo apt install git build-essential bc bison flex libssl-dev \
libelf-dev dwarves pahole ccache sparse coccinelle \
clang llvm qemu-system-x86


make localmodconfig

qemu-system-x86_64 \
-kernel arch/x86/boot/bzImage \
-m 2G \
-nographic \
-append "console=ttyS0"




qemu-system-x86_64 \
-kernel arch/x86/boot/bzImage \
-initrd ~/work/MY_TMP/initramfs.cpio.gz \
-nographic \
-append "console=ttyS0"



------------------------

irc


/server add oftc irc.oftc.net
/connect oftc

/join #kernelnewbies

/nick oriolp
/join #kernelnewbies
/part
/msg username hello
/quit


/msg john hello I saw your patch on LKML


#kernelnewbies
#linux-kernel
#linux-arm
#linux-mm
#netdev


-----------------------

tmux ls
tmux attach
cntl-b +-num



----------------

build initramfs

sudo apt install busybox-static


#minimal rootfs
mkdir rootfs
cd rootfs

mkdir -p {bin,sbin,etc,proc,sys,usr/bin,usr/sbin}

cp /bin/busybox bin/

ln -s busybox bin/sh


vi init:
#!/bin/sh
mount -t proc none /proc
mount -t sysfs none /sys

echo "Booted minimal kernel!"

exec /bin/sh



chmod +x init
find . | cpio -o -H newc | gzip > ../initramfs.cpio.gz
source poky/oe-init-build-env build-rpi3
bitbake uri-image
sudo bmaptool copy tmp/deploy/images/raspberrypi3-64/uri-image-raspberrypi3-64.rootfs.wic.bz2 /dev/sdc
sudo losetup -Pf image.wic 
sudo losetup -a 
sudo mount /dev/loop0p2 /mnt/wic-root/ 



Flashear toda sd:
rootfs-switch nfs
reboot

bzcat tmp/deploy/images/raspberrypi3-64/uri-image-raspberrypi3-64.rootfs.wic.bz2 | ssh root@192.168.0.100 "dd of=/dev/mmcblk0 bs=4M"


--------
bunzip2 -c tmp/deploy/images/raspberrypi3-64/uri-image-raspberrypi3-64.rootfs.wic.bz2 > image2.wic
wic ls build-rpi3/image2.wic:1/overlays 



https://bootlin.com/labs/doc/nunchuk.pdf


-------
devtool modify kernel-module-nunchuk

#editar aqui.c
workspace/sources/kernel-module-nunchuk/

devtool build kernel-module-nunchuk

devtool deploy-target kernel-module-nunchuk root@target

devtool reset kernel-module-nunchuk
