FROM openjdk:17-jdk

WORKDIR /app

COPY localStore/messageApplication-0.0.1-SNAPSHOT.jar /app/messageApplication.jar

ENTRYPOINT ["java", "-jar", "/app/messageApplication.jar"]
