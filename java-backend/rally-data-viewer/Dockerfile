# Build stage
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Create a non-root user
RUN addgroup --system javauser && adduser --system --ingroup javauser javauser
USER javauser

# Expose the port the app runs on
ENV PORT=8080
EXPOSE ${PORT}

# Command to run the application
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT} --server.address=0.0.0.0"] 