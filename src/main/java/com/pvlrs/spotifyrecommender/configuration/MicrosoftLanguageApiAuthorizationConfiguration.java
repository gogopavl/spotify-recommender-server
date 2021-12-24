package com.pvlrs.spotifyrecommender.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrosoftLanguageApiAuthorizationConfiguration {

    private final String MS_AUTHORIZATION_HEADER = "Ocp-Apim-Subscription-Key";

    @Value("${com.microsoft.language-api.key}")
    private String microsoftLanguageApiKey;

    @Bean
    public RequestInterceptor microsoftLanguageApiClientRequestInterceptor() {
        return (requestTemplate) -> requestTemplate.header(MS_AUTHORIZATION_HEADER, microsoftLanguageApiKey);
    }
}
