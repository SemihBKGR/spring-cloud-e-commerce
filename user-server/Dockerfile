FROM openjdk:11.0.7-jre-slim
MAINTAINER semihbkgr
RUN mkdir app
COPY ./target/user.jar /app/app.jar
WORKDIR /app
EXPOSE 9010
ENTRYPOINT ["java","-jar","app.jar"]