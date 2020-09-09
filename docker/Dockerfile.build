FROM centos:7

# Install Azure-CLI repo
RUN rpm --import https://packages.microsoft.com/keys/microsoft.asc 
RUN sh -c 'echo -e "[azure-cli]" > /etc/yum.repos.d/azure-cli.repo'                                                && \
    sh -c 'echo -e "name=Azure CLI" >> /etc/yum.repos.d/azure-cli.repo'                                            && \
    sh -c 'echo -e "baseurl=https://packages.microsoft.com/yumrepos/azure-cli" >> /etc/yum.repos.d/azure-cli.repo' && \
    sh -c 'echo -e "enabled=1 >> /etc/yum.repos.d/azure-cli.repo"'                                                 && \
    sh -c 'echo -e "gpgcheck=1 >> /etc/yum.repos.d/azure-cli.repo"'                                                && \
    sh -c 'echo -e "gpgkey=https://packages.microsoft.com/keys/microsoft.asc" >> /etc/yum.repos.d/azure-cli.repo' 

# Install packages
RUN yum install -y             \
        java-11-openjdk-devel  \
        make                   \
        wget                   \
        which                  \
        git                    \
        yum-utils              \
        azure-cli           

RUN  yum-config-manager --add-repo  https://download.docker.com/linux/centos/docker-ce.repo && \
        yum install -y docker-ce docker-ce-cli containerd.io                                && \
        yum clean all                                                                       && \
        rm -rf /var/cache/yum

# Install maven
RUN cd /var/tmp && \
    wget http://www.gtlib.gatech.edu/pub/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz && \
    tar xf apache-maven-3.6.3-bin.tar.gz -C /usr/lib                                                       && \
    ln -s /usr/lib/apache-maven-3.6.3/bin/mvn /usr/bin/mvn

RUN mkdir repo
WORKDIR /repo