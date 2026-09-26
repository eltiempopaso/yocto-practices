FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

RUN apt-get update && apt-get install -y \
    build-essential \
    chrpath \
    cpio \
    debianutils \
    diffstat \
    file \
    gawk \
    gcc \
    git \
    iputils-ping \
    libacl1 \
    libcrypt-dev \
    locales \
    python3 \
    python3-git \
    python3-jinja2 \
    python3-pexpect \
    python3-pip \
    python3-subunit \
    socat \
    texinfo \
    unzip \
    wget \
    xz-utils \
    zstd \
    lz4 \
    sudo \
    vim \
    nano \
    less \
    rsync \
    && rm -rf /var/lib/apt/lists/*

# Generate UTF-8 locale required by Yocto
RUN sed -i 's/^# *en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen \
    && locale-gen

# Create a normal user
#ARG USERNAME=yocto
#ARG UID=1000
#ARG GID=1000

#RUN groupadd --gid ${GID} ${USERNAME} \
#    && useradd --uid ${UID} --gid ${GID} \
#       --create-home \
#       --shell /bin/bash \
#       ${USERNAME} \
#    && echo "${USERNAME} ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/${USERNAME} \
#    && chmod 0440 /etc/sudoers.d/${USERNAME}

#RUN useradd --create-home \
#    --shell /bin/bash \
#    ${USERNAME} \
#    && usermod -aG sudo ${USERNAME} \
#    && echo "${USERNAME} ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/${USERNAME} \
#    && chmod 0440 /etc/sudoers.d/${USERNAME}

#USER ${USERNAME}
WORKDIR /work

CMD ["/bin/bash"]
