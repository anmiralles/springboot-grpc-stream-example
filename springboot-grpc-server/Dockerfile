# Start with the UBI 8 OpenJDK 21 base image
FROM registry.access.redhat.com/ubi8/openjdk-21:latest

# Set working directory in container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
# Assuming your built JAR file is named "app.jar" and located in the same directory as the Dockerfile
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar

# Expose the application port
EXPOSE 8080 9090

# Define the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]