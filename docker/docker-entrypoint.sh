#!/usr/bin/env sh
set -e
echo "" > /usr/src/vlol/application.override.properties 
if [ -z "${ENVIRONMENT}" ]; then
    echo "ENVIRONMENT environment variable not found. Setting to development. Specify ENVIRONMENT=PROD for production"
else
	if [ "${ENVIRONMENT}" == "PROD" ]; then
		echo "spring.datasource.data=" >> /usr/src/vlol/application.override.properties
    fi
fi

if [ -z "${DBURL}" ]; then
    echo "DBURL environment variable not found. Setting to embedded db default."
else
	echo "spring.datasource.url=${DBURL}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${DBUSERNAME}" ]; then
    echo "DBUSERNAME environment variable not found. Setting to embedded db default."
else
	echo "spring.datasource.username=${DBUSERNAME}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${DBPASSWORD}" ]; then
    echo "DBPASSWORD environment variable not found. Setting to embedded db default."
else
	echo "spring.datasource.password=${DBPASSWORD}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${DBDIALECT}" ]; then
    echo "DBDIALECT environment variable not found. Setting to embedded db."
    echo "See https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/dialect/Dialect.html for available dialects"
    echo "Should be in to format ex: org.hibernate.dialect.H2Dialect"
else
	echo "spring.jpa.properties.hibernate.dialect=${DBDIALECT}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_HOST}" ]; then
    echo "SMTP_HOST environment variable not found. Defaulting to development host"
else
	echo "mail.smtp.host=${SMTP_HOST}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_PORT}" ]; then
    echo "SMTP_PORT environment variable not found. Defaulting to port 465"
	echo "mail.smtp.port=465" >> /usr/src/vlol/application.override.properties 
else
	echo "mail.smtp.port=${SMTP_PORT}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_USERNAME}" ]; then
    echo "SMTP_USERNAME environment variable not found. Defaulting to development username"
else
	echo "mail.smtp.username=${SMTP_USERNAME}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_PASSWORD}" ]; then
    echo "SMTP_PASSWORD environment variable not found. Defaulting to development password"
else
	echo "mail.smtp.password=${SMTP_PASSWORD}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_NO_REPLY_EMAIL}" ]; then
    echo "SMTP_NO_REPLY_EMAIL environment variable not found. Defaulting to development email"
else
	echo "mail.smtp.noReplyEmail=${SMTP_NO_REPLY_EMAIL}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_SUPPORT_EMAIL}" ]; then
    echo "SMTP_SUPPORT_EMAIL environment variable not found. Defaulting to development email"
else
	echo "mail.smtp.supportEmail=${SMTP_SUPPORT_EMAIL}" >> /usr/src/vlol/application.override.properties 
fi

if [ -z "${SMTP_DOMAIN_URL}" ]; then
    echo "SMTP_DOMAIN_URL environment variable not found. Defaulting to http://localhost:5000/"
else
	echo "mail.smtp.urlPath=${SMTP_DOMAIN_URL}" >> /usr/src/vlol/application.override.properties 
fi


echo ""
echo "Starting VLOL"
# launch the processes supervisor
java -jar /usr/src/vlol/app.jar --spring.config.location=classpath:application.properties file:///usr/src/vlol/application.override.properties
