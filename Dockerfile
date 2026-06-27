# Stage 1: Download the dependencies
FROM eclipse-temurin:21-jdk-alpine AS dependencies

# Downloads maven and prevents apk from storing temp files
RUN apk add --no-cache maven

# Working directory
WORKDIR /build 

# Copy pom.xml to the working directory
COPY pom.xml .

# Tells maven to download all dependencies and store them in the local maven cache in the container
RUN mvn dependency:go-offline



# Stage 2: Build the application
FROM dependencies AS builder
COPY src ./src
RUN mvn clean package -DskipTests



# Stage 3: Run the application
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]





