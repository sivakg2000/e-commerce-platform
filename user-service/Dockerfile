#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
#ENV MAVEN_OPTS="-XX:+TieredCompilation --XX:TieredStopAtLevel=1"
WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml clean install -Dmaven.test.skip=true

#
# Package Stage
#
#FROM openjdk:17.0.1-jdk-slim
FROM openjdk:17-jdk-alpine
WORKDIR /home/app
COPY --from=build /home/app/target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","user-service-0.0.1-SNAPSHOT.jar"]