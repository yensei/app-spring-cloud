# Antes de construir requiere utilizar mvn clean package para generar el JAR
FROM openjdk:17-jdk-alpine
# RUN apk add curl --no-cache
VOLUME /tmp
ARG HOSTNAME customers
ADD target/customers-*.jar customers.jar
RUN sh -c 'touch /customers.jar'
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/customers.jar" ]