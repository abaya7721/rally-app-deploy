# Build stage
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY target/rally-data-viewer-0.0.1-SNAPSHOT.jar app.jar

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/app.jar app.jar

# Create a non-root user
RUN addgroup --system javauser && adduser --system --ingroup javauser javauser
USER javauser

# Expose the port the app runs on
ENV PORT=8080
EXPOSE ${PORT}

# Command to run the application with debug logging
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT} --server.address=0.0.0.0 --logging.level.org.springframework.web=DEBUG --logging.level.com.rally=DEBUG"] 