package com.pvlrs.spotifyrecommender.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpotifyAuthorizationConstants {

    public static final String SPOTIFY_AUTH_CLIENT_ID = "spotify-auth";
    public static final String AUTH_TOKEN_KEY = "key";
    public static final String AUTH_TOKEN_PRINCIPAL = "principal";
    public static final String AUTH_TOKEN_ROLE = "ROLE_ANONYMOUS";
    public static final String BEARER_TOKEN_PLACEHOLDER = "Bearer {0}";
}
