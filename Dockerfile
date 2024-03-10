FROM tomcat
LABEL authors="Денис"

COPY /target/root.war /usr/local/tomcat/webapps

