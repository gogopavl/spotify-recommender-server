#!/bin/bash
cd ..
./mvnw clean package -DskipTests
docker build -t gogopavl/spotify-recommender-server:latest .
docker push gogopavl/spotify-recommender-server:latest
