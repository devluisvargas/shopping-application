FROM maven:3.8.1-openjdk-17-slim as builder

WORKDIR /app/inventory-service

COPY pom.xml /app
COPY pom.xml .

RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/inventory-service/target/inventory-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

CMD ["java", "-jar", "inventory-service-0.0.1-SNAPSHOT.jar"]