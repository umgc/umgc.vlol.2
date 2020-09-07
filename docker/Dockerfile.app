FROM ubuntu:20.10
USER root
ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update && \
	apt-get --no-install-recommends -y install default-jre openjdk-11-jdk
	
RUN apt-get update && apt-get install -y --no-install-recommends supervisor postfix opendkim rsyslog mailutils openssl && \
    rm -rf /var/lib/apt/lists/* 

LABEL VERSION=$VERSION
RUN mkdir /usr/src/vlol

ARG VLOL_APP
COPY $VLOL_APP /usr/src/vlol/app.jar

EXPOSE 5000

#RUN openssl genrsa -out mail.domain.tld.key 2048 && \
#    chmod 600 mail.domain.tld.key && \
#    yes "" | openssl req -new -key mail.domain.tld.key -out mail.domain.tld.csr && \
#    openssl x509 -req -days 365 -in mail.domain.tld.csr -signkey mail.domain.tld.key -out mail.domain.tld.crt && \
#    openssl rsa -in mail.domain.tld.key -out mail.domain.tld.key.nopass && \
#    mv mail.domain.tld.key.nopass mail.domain.tld.key && \
#    yes "" | openssl req -nodes -new -x509 -keyout cakey.pem -out cacert.pem -days 3650 && \
#    chmod 600 mail.domain.tld.key && \
#    chmod 600 cakey.pem && \
#    mv mail.domain.tld.key /etc/ssl/private/ && \
#    mv mail.domain.tld.crt /etc/ssl/certs/ && \
#    mv cakey.pem /etc/ssl/private/ && \
#    mv cacert.pem /etc/ssl/certs/

# add configuration files
ADD ./docker/config/supervisord/supervisord.conf   /etc/supervisor/supervisord.conf
ADD ./docker/config/rsyslog/rsyslog.conf           /etc/rsyslog.conf
ADD ./docker/config/postfix/main.cf                /etc/postfix/main.cf
ADD ./docker/config/postfix/header_checks          /etc/postfix/header_checks
ADD ./docker/config/opendkim/opendkim.conf         /etc/opendkim.conf
ADD ./docker/config/opendkim/opendkim              /etc/default/opendkim
ADD ./docker/config/opendkim/TrustedHosts          /etc/opendkim/TrustedHosts
ADD ./docker/config/opendkim/SigningTable          /etc/opendkim/SigningTable
ADD ./docker/config/opendkim/KeyTable              /etc/opendkim/KeyTable

# configure the entrypoint
ADD ./docker/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh


ENTRYPOINT ["/docker-entrypoint.sh"]
