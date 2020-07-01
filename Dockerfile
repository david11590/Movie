FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8080

ADD target/libs/movie-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "movie-0.0.1-SNAPSHOT.jar"]
