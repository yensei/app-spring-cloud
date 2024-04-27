FROM openjdk:17-jdk-alpine
LABEL mantainer="yensei"

COPY target/config-server-*.jar config-server.jar
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-server.jar" ]