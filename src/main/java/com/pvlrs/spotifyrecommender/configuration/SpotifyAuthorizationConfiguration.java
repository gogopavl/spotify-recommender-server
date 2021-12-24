package com.pvlrs.spotifyrecommender.configuration;

import com.pvlrs.spotifyrecommender.service.OAuth2TokenProvider;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import static com.pvlrs.spotifyrecommender.constants.SpotifyAuthorizationConstants.SPOTIFY_AUTH_CLIENT_ID;

public class SpotifyAuthorizationConfiguration {

    private final OAuth2TokenProvider tokenProvider;

    public SpotifyAuthorizationConfiguration(OAuth2TokenProvider oAuth2TokenProvider) {
        this.tokenProvider = oAuth2TokenProvider;
    }

    @Bean
    public RequestInterceptor spotifyClientRequestInterceptor() {
        return (requestTemplate) -> {
            String token = tokenProvider.getAuthenticationToken(SPOTIFY_AUTH_CLIENT_ID);
            requestTemplate.header(HttpHeaders.AUTHORIZATION, token);
        };
    }
}
