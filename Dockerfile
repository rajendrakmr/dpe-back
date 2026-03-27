# ---------- Stage 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom first
COPY pom.xml .

# Create dummy groovy-all jar
RUN mkdir -p /tmp/dummy && \
    cd /tmp/dummy && \
    jar cf groovy-all-3.0.25.jar .

# Install dummy jar into Maven repo
RUN mvn install:install-file \
    -DgroupId=org.codehaus.groovy \
    -DartifactId=groovy-all \
    -Dversion=3.0.25 \
    -Dpackaging=jar \
    -Dfile=/tmp/dummy/groovy-all-3.0.25.jar

# Copy source
COPY src ./src

# Build project (force update)
RUN mvn clean package -U -DskipTests


# ---------- Stage 2: Run ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/app/app.jar"]
