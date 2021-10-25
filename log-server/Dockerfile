FROM openjdk:11.0.7-jre-slim
MAINTAINER semihbkgr
RUN mkdir app
COPY ./target/log.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]