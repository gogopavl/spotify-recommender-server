FROM maven:3-jdk-11

ADD . /spotify-recommender
WORKDIR /spotify-recommender

RUN ls -l
RUN mvn clean install -DskipTests

FROM openjdk:11.0-jre-slim

VOLUME /tmp
COPY --from=0 /spotify-recommender/target/spotify-recommender*.jar app.jar

CMD ["sh", "-c", "java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=cloud -jar /app.jar"]
