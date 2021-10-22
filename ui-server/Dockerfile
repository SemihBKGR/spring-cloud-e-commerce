FROM openjdk:11.0.7-jre-slim
MAINTAINER semihbkgr
RUN mkdir app
COPY ./target/ui.jar /app/app.jar
WORKDIR /app
EXPOSE 9999
ENTRYPOINT ["java","-jar","app.jar"]