# Stage 1: Use a lightweight JDK image
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR file from your local machine into the Docker image
COPY build/libs/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
