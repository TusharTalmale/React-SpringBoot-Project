# Use Maven with JDK 22 for building the application
FROM maven:3.9.6-eclipse-temurin-22 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK 22 for running the application
FROM openjdk:22-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/email-writer-sb-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/email-writer-sb-0.0.1-SNAPSHOT.jar"]
