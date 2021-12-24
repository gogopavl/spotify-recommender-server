package com.pvlrs.spotifyrecommender.client;

import com.pvlrs.spotifyrecommender.configuration.MicrosoftFaceApiAuthorizationConfiguration;
import com.pvlrs.spotifyrecommender.dto.ImageAnalysisDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microsoft-face-api",
        url = "${com.microsoft.face-api.host}",
        configuration = MicrosoftFaceApiAuthorizationConfiguration.class)
public interface MicrosoftFaceApiClient {

    @PostMapping("/face/v1.0/detect")
    @Headers({"Content-Type: application/octet-stream"})
    List<ImageAnalysisDto> getImageEmotions(
            byte[] image,
            @RequestParam(value = "returnFaceAttributes",
                    required = false, defaultValue = "emotion") String attributes,
            @RequestParam(value = "returnFaceId",
                    required = false, defaultValue = "true") Boolean returnFaceId,
            @RequestParam(value = "recognitionModel",
                    required = false, defaultValue = "recognition_04") String recognitionModel);
}
