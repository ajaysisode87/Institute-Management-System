FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} institute-management-system.jar

ENTRYPOINT ["java","-jar","/institute-management-system.jar"]

EXPOSE 8787