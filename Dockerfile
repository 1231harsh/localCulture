# Use a base image with JDK 21
FROM maven:3.8.6-openjdk-21 as build

# Set the working directory in the container
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:21-jdk-slim
# Copy the Maven build output (JAR file) to the container
COPY --from=build /app/target/*.jar /app/localculture.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "localculture.jar"]
