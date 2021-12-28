#!/bin/bash
docker logout
docker login spotifyrecommender.azurecr.io
docker tag gogopavl/spotify-recommender-server spotifyrecommender.azurecr.io/spotify-recommender-server
docker push spotifyrecommender.azurecr.io/spotify-recommender-server
