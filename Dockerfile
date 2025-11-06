# Use an official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src ./src

# Package the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/movie-ticket-booking-0.0.1-SNAPSHOT.jar"]
