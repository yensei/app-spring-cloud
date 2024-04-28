# Antes de construir requiere utilizar mvn clean package para generar el JAR
FROM openjdk:17-jdk-alpine
# RUN apk add curl --no-cache
VOLUME /tmp
ARG HOSTNAME gateway
ADD target/gateway-*.jar gateway.jar
RUN sh -c 'touch /gateway.jar'
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar" ]