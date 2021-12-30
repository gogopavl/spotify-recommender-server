# Moodify

## About

- Web application that recommends spotify playlists based on user emotion
- Production url: [moodify.pvlrs.com](https://moodify.pvlrs.com)

## Developing

> Populate your `application.properties` file based on the template provided - [`application-template.properties`](src/main/resources/application-template.properties)

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Setting Up Postman

- Create an environment (e.g. `Moodify Environment`) and define a variable named `REC_SERVER_URL` that holds the value `http://localhost:8080/spotify-recommender`
- Then all the API calls defined in the Postman collection ([postman/moodify-postman-collection.json](postman/moodify-postman-collection.json)) should be routed to your local application server

## Containerizing and Running an Image

- To build an image
    ```bash
    ./mvnw clean package -DskipTests
    docker build -t gogopavl/spotify-recommender-server:latest .
    ```
- To run
    ```bash
    docker run -d -p 8080:8080 --name moodify-server gogopavl/spotify-recommender-server
    ```