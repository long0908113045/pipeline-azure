FROM node:16.14.0-alpine AS app-base
FROM gradle:7.5.1-jdk17 AS build

COPY . /app

WORKDIR /app

CMD npm install
CMD ./gradlew build

WORKDIR /app/build/libs

CMD java -jar pipeline-azure-0.0.1-SNAPSHOT.jar
