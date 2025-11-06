# Use an official OpenJDK image as the base
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src ./src

# Package the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Expose the port Spring Boot runs on
EXPOSE 8090

# Run the application
CMD ["java", "-jar", "target/movie-ticket-booking-0.0.1-SNAPSHOT.jar"]
