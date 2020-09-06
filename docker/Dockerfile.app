FROM openjdk:11

LABEL VERSION=$VERSION
RUN mkdir /usr/src/vlol

ARG VLOL_APP
COPY $VLOL_APP /usr/src/vlol/app.jar
COPY vloldb.mv.db /
COPY vloldb.trace.db /

EXPOSE 5000

ENTRYPOINT ["java","-jar", "/usr/src/vlol/app.jar"]
