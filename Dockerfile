FROM openjdk:8-jdk-alpine
COPY target/zedelivery-partner-challenge-1.0.0.jar partner-challenge-1.0.0.jar
ENTRYPOINT ["java","-jar","/partner-challenge-1.0.0.jar"]