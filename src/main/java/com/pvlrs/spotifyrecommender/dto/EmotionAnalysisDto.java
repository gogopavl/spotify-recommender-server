package com.pvlrs.spotifyrecommender.dto;

import com.pvlrs.spotifyrecommender.dto.ImageAnalysisDto.EmotionAttribute;
import com.pvlrs.spotifyrecommender.dto.TextAnalysisDto.ConfidenceScores;
import com.pvlrs.spotifyrecommender.dto.TextAnalysisDto.TextDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionAnalysisDto {

    private String overallSentiment;

    // From text analysis
    private Float negative;
    private Float positive;

    // From image analysis
    private Float anger;
    private Float contempt;
    private Float disgust;
    private Float fear;
    private Float happiness;
    private Float sadness;
    private Float surprise;

    // Common
    private Float neutral;

    public static EmotionAnalysisDto fromTextDocument(TextDocument textDocument) {
        ConfidenceScores confidenceScores = textDocument.getConfidenceScores();
        return EmotionAnalysisDto.builder()
                .overallSentiment(textDocument.getSentiment())
                .positive(confidenceScores.getPositive())
                .neutral(confidenceScores.getNeutral())
                .negative(confidenceScores.getNegative()).build();
    }

    public static EmotionAnalysisDto fromImageAnalysisDto(ImageAnalysisDto imageAnalysisDto) {
        EmotionAttribute emotionAttributes = imageAnalysisDto.getFaceAttributes().getEmotion();
        return EmotionAnalysisDto.builder()
                .anger(emotionAttributes.getAnger())
                .contempt(emotionAttributes.getContempt())
                .disgust(emotionAttributes.getDisgust())
                .fear(emotionAttributes.getFear())
                .happiness(emotionAttributes.getHappiness())
                .sadness(emotionAttributes.getSadness())
                .surprise(emotionAttributes.getSurprise())
                .neutral(emotionAttributes.getNeutral()).build();
    }
}
