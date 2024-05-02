# Antes de construir requiere utilizar mvn clean package para generar el JAR
FROM openjdk:17-jdk-alpine
# RUN apk add curl --no-cache
VOLUME /tmp
ADD target/admin-*.jar admin.jar

ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin.jar" ]