# Use a base image with JDK 21
FROM eclipse-temurin:11-jdk as builder

# Set the working directory in the container
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
# Copy the Maven build output (JAR file) to the container
COPY --from=builder /app/target/*.jar /app/localculture.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "localculture.jar"]
