FROM openjdk:11.0-jre-slim

ARG JAR_FILE=target/spotify-recommender*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
