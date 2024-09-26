# Use the official OpenJDK 17 image with Alpine Linux as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot application JAR file into the container
COPY target/examserver-0.0.1-SNAPSHOT.jar app.jar

# Set environment variables
ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}

# Expose port 8080 to the outside world (the default port for Spring Boot applications)
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java","-jar","/app/app.jar"]
