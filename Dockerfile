# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from localStore directory to the working directory in the container
COPY localStore/messageApplication-0.0.1-SNAPSHOT.jar /app/messageApplication.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/messageApplication.jar"]
