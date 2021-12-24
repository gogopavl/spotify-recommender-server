package com.pvlrs.spotifyrecommender.service;

import com.pvlrs.spotifyrecommender.client.MicrosoftFaceApiClient;
import com.pvlrs.spotifyrecommender.client.MicrosoftLanguageApiClient;
import com.pvlrs.spotifyrecommender.client.request.MsTextAnalysisRequest;
import com.pvlrs.spotifyrecommender.client.request.MsTextAnalysisRequest.TextDocument;
import com.pvlrs.spotifyrecommender.controller.request.TextAnalysisRequest;
import com.pvlrs.spotifyrecommender.dto.EmotionAnalysisDto;
import com.pvlrs.spotifyrecommender.dto.ImageAnalysisDto;
import com.pvlrs.spotifyrecommender.dto.TextAnalysisDto;
import com.pvlrs.spotifyrecommender.exception.AnalysisException;
import com.pvlrs.spotifyrecommender.utilities.ImageToEmotionAnalysisMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmotionAnalysisService {

    private final MicrosoftFaceApiClient faceApiClient;
    private final MicrosoftLanguageApiClient languageApiClient;

    public EmotionAnalysisService(MicrosoftFaceApiClient faceApiClient,
                                  MicrosoftLanguageApiClient languageApiClient) {
        this.faceApiClient = faceApiClient;
        this.languageApiClient = languageApiClient;
    }

    public EmotionAnalysisDto analyzeText(TextAnalysisRequest request) {
        TextDocument textDocument = TextDocument.builder()
                .id(UUID.randomUUID())
                .text(request.getText()).build();
        MsTextAnalysisRequest textAnalysisRequest = MsTextAnalysisRequest.builder()
                .documents(List.of(textDocument)).build();
        TextAnalysisDto textAnalysis = languageApiClient.getTextEmotions(textAnalysisRequest, true);
        return textAnalysis.getDocuments().stream()
                .findFirst()
                .map(EmotionAnalysisDto::fromTextDocument)
                .orElseThrow(() -> new AnalysisException("Was not able to analyze the provided text at this time."));
    }

    public EmotionAnalysisDto analyzeImage(byte[] image) {
        List<ImageAnalysisDto> imageAnalysis = faceApiClient.getImageEmotions(image, "emotion", null, null);
        return imageAnalysis.stream()
                .findFirst()
                .map(ImageToEmotionAnalysisMapper::toEmotionAnalysis)
                .orElseThrow(() -> new AnalysisException("Was not able to analyze the provided image at this time."));
    }
}
