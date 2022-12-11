FROM node:16.14.0-alpine AS app-base
FROM gradle:7.5.1-jdk17 AS build

ARG JAR_FILE=build/libs/ExamDevopsRepository-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
