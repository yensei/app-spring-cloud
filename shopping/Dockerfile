    # Stage 1: Build the application JAR
    # FROM maven:3-jdk-8-alpine AS builder

    # RUN mkdir -p app
    # ENV home=/app
    # COPY pom.xml .${home}/pom.xml
    # WORKDIR ${home}

    # RUN mvn -B dependency:install
    # RUN mvn clean package

    # Stage 2 Deploy
    # Antes de construir requiere utilizar mvn clean package para generar el JAR
    FROM openjdk:17-jdk-alpine

    VOLUME /tmp
    # COPY --from=builder ${home}/target/shopping-*.jar shopping.jar
    COPY /target/shopping-*.jar shopping.jar

    ENV EXTRA_ARGS="" 
    ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/shopping.jar", "${EXTRA_ARGS:-}" ]