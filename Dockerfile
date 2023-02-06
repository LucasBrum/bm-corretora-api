#
# Build stage
#
FROM maven:3.6.3-openjdk-17 AS build
COPY . .
RUN mvn clean package

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /target/bm-corretora-api-0.0.1-SNAPSHOT.jar bm-corretora-api-1.0.0.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]