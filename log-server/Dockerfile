FROM openjdk:11.0.7-jre-slim
MAINTAINER semihbkgr
RUN mkdir app
COPY ./target/log.jar /app/app.jar
WORKDIR /app
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]