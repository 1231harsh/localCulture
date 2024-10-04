# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Copy source code
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Use a smaller image for the final deployment
FROM openjdk:17-jdk-slim

# Copy the jar file from the builder image
COPY --from=builder /app/target/localCulture-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
