FROM iits/jdk17-temurin:jdk17-temurin-alpine

WORKDIR /opt/app

ARG JAR_FILE=target/gerenciador-biblioteca-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]