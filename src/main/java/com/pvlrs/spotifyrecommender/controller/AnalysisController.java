package com.pvlrs.spotifyrecommender.controller;

import com.pvlrs.spotifyrecommender.controller.request.TextAnalysisRequest;
import com.pvlrs.spotifyrecommender.dto.EmotionAnalysisDto;
import com.pvlrs.spotifyrecommender.service.EmotionAnalysisService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping("/v1/analyses")
@RestController
public class AnalysisController {

    private final EmotionAnalysisService emotionAnalysisService;

    public AnalysisController(EmotionAnalysisService emotionAnalysisService) {
        this.emotionAnalysisService = emotionAnalysisService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmotionAnalysisDto analyseTextEmotion(@RequestBody TextAnalysisRequest request) {
        return emotionAnalysisService.analyzeText(request);
    }

    @PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public EmotionAnalysisDto analyseImageEmotion(final HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        return emotionAnalysisService.analyzeImage(inputStream.readAllBytes());
    }
}
