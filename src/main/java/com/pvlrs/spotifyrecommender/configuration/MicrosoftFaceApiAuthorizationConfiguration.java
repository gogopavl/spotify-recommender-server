package com.pvlrs.spotifyrecommender.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrosoftFaceApiAuthorizationConfiguration {

    private final String MS_AUTHORIZATION_HEADER = "Ocp-Apim-Subscription-Key";

    @Value("${com.microsoft.face-api.key}")
    private String microsoftFaceApiKey;

    @Bean
    public RequestInterceptor microsoftFaceApiClientRequestInterceptor() {
        return (requestTemplate) -> requestTemplate.header(MS_AUTHORIZATION_HEADER, microsoftFaceApiKey);
    }
}
