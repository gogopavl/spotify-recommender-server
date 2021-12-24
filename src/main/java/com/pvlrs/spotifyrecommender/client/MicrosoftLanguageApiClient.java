package com.pvlrs.spotifyrecommender.client;

import com.pvlrs.spotifyrecommender.client.request.MsTextAnalysisRequest;
import com.pvlrs.spotifyrecommender.configuration.MicrosoftLanguageApiAuthorizationConfiguration;
import com.pvlrs.spotifyrecommender.dto.TextAnalysisDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microsoft-language-api",
        url = "${com.microsoft.language-api.host}",
        configuration = MicrosoftLanguageApiAuthorizationConfiguration.class)
public interface MicrosoftLanguageApiClient {

    @PostMapping("/text/analytics/v3.2-preview.1/sentiment")
    TextAnalysisDto getTextEmotions(
            @RequestBody MsTextAnalysisRequest request,
            @RequestParam(value = "opinionMining", required = false, defaultValue = "true") Boolean opinionMining);
}
