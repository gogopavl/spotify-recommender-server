package com.pvlrs.spotifyrecommender.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import static com.pvlrs.spotifyrecommender.constants.SpotifyAuthorizationConstants.AUTH_TOKEN_KEY;
import static com.pvlrs.spotifyrecommender.constants.SpotifyAuthorizationConstants.AUTH_TOKEN_PRINCIPAL;
import static com.pvlrs.spotifyrecommender.constants.SpotifyAuthorizationConstants.AUTH_TOKEN_ROLE;
import static com.pvlrs.spotifyrecommender.constants.SpotifyAuthorizationConstants.BEARER_TOKEN_PLACEHOLDER;

@Service
public class OAuth2TokenProvider {

    // Using anonymous user principal as its S2S authentication
    private static final Authentication ANONYMOUS_USER_AUTHENTICATION = new AnonymousAuthenticationToken(
            AUTH_TOKEN_KEY, AUTH_TOKEN_PRINCIPAL, AuthorityUtils.createAuthorityList(AUTH_TOKEN_ROLE));

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2TokenProvider(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAuthenticationToken(final String authorizationServerName) {

        final OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId(authorizationServerName)
                .principal(ANONYMOUS_USER_AUTHENTICATION).build();

        String token = authorizedClientManager.authorize(request).getAccessToken().getTokenValue();
        return MessageFormat.format(BEARER_TOKEN_PLACEHOLDER, token);
    }
}
