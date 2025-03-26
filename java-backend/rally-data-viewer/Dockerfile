# Build stage
FROM --platform=$BUILDPLATFORM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM --platform=$TARGETPLATFORM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Install wget for healthcheck
RUN apk add --no-cache wget

# Create a non-root user
RUN addgroup --system javauser && adduser --system --ingroup javauser javauser
USER javauser

# Add healthcheck with longer startup period
HEALTHCHECK --interval=10s --timeout=5s --start-period=120s --retries=5 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT}/actuator/health || exit 1

# Command to run the application with container-optimized settings
ENTRYPOINT ["sh", "-c", "java \
  -XX:+UseG1GC \
  -XX:MaxGCPauseMillis=200 \
  -XX:+UseStringDeduplication \
  -XX:+ParallelRefProcEnabled \
  -XX:+UseContainerSupport \
  -XX:MaxRAMPercentage=75.0 \
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/tmp \
  -jar app.jar"] 