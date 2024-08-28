# Use a base image with JDK 21
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build output (JAR file) to the container
COPY --from=build /app/target/*.jar /app/localculture.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "localculture.jar"]
