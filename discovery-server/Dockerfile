FROM openjdk:11.0.7-jre-slim
MAINTAINER semihbkgr
RUN mkdir app
COPY ./target/discovery.jar /app/app.jar
WORKDIR /app
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]