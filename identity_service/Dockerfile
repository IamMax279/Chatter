FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine AS prod
WORKDIR /app
COPY --from=build /app/target/Scrapr-server-0.0.1-SNAPSHOT.jar target/Scrapr-server-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/Scrapr-server-0.0.1-SNAPSHOT.jar"]
