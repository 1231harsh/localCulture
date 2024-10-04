FROM openjdk:21-jdk


WORKDIR /app


COPY target/localCulture-0.0.1-SNAPSHOT.jar /app/localCulture.jar


ENTRYPOINT ["java", "-jar", "/app/localCulture.jar"]