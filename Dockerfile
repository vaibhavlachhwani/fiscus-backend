# Build stage (Upgraded to Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run stage (Upgraded to Java 21)
FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /target/fiscus-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]