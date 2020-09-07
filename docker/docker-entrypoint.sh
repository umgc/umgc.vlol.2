#!/usr/bin/env sh
set -e

# read url from environment
if [ -z "${DBURL}" ]; then
    echo "DBURL environment variable not found. Setting to embedded db."
	DBURL="jdbc:h2:file:./vloldb"
fi
# read url from environment
if [ -z "${DBUSERNAME}" ]; then
    echo "DBUSERNAME environment variable not found. Setting to sa."
	DBUSERNAME="sa"
fi
# read url from environment
if [ -z "${DBPASSWORD}" ]; then
    echo "DBPASSWORD environment variable not found. Setting to blank."
	DBPASSWORD=""
fi

# read domain from environment
if [ -z "${DOMAIN}" ]; then
    echo "DOMAIN environment variable not found. Defaulting to localhost"
    DOMAIN="localhost"
fi

# TODO: escape domain!

# replace the placeholders in the configuration files
PATTERN="s/\${DOMAIN}/${DOMAIN}/g"
sed -i ${PATTERN} /etc/postfix/main.cf
sed -i ${PATTERN} /etc/opendkim/SigningTable
sed -i ${PATTERN} /etc/opendkim/KeyTable

# check the presence of the key for opendkim
if [ ! -f /etc/opendkim/domainkeys/mail.private ]; then
    echo "Cannot load the 'mail.private' file from '/etc/opendkim/domainkeys/mail.private'. Please mount it as a Docker volume before starting the container."
	echo "-v /path/to/mail.private:/etc/opendkim/domainkeys/mail.private"
    echo "If you do not have a signing key, create one"
    echo "opendkim-genkey -s mail -d example.com"
    echo "Add to your DNS records:"
	echo "TXT mail._domainkey.example.com "'"'"v=DKIM1; k=rsa; p=...private key..."'"'
    echo "DKIM Signing will be disabled."
else
	sed -i "s/#odkim //g" /etc/postfix/main.cf
	sed -i "s/#odkim //g" /etc/supervisor/supervisord.conf
    # fix permissions on files
    chown opendkim:opendkim /etc/opendkim/domainkeys
    chown opendkim:opendkim /etc/opendkim/domainkeys/mail.private
    chmod 400 /etc/opendkim/domainkeys/mail.private
fi

echo "spring.datasource.url=${DBURL}" > /usr/src/vlol/application.override.properties 
echo "spring.datasource.username=${DBUSERNAME}" >> /usr/src/vlol/application.override.properties 
echo "spring.datasource.password=${DBPASSWORD}" >> /usr/src/vlol/application.override.properties 

echo ""
echo "Starting VLOL"
# launch the processes supervisor
/usr/bin/supervisord -c /etc/supervisor/supervisord.conf
