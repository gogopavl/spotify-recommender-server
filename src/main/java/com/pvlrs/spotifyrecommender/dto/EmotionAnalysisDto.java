package com.pvlrs.spotifyrecommender.dto;

import com.pvlrs.spotifyrecommender.dto.TextAnalysisDto.ConfidenceScores;
import com.pvlrs.spotifyrecommender.dto.TextAnalysisDto.TextDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

import static com.pvlrs.spotifyrecommender.constants.EmotionAnalysisUserMessageTemplates.getRandomUserMessageTemplate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionAnalysisDto {

    private String overallSentiment;
    private String userMessage;

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
        String userMessage = MessageFormat.format(getRandomUserMessageTemplate(), textDocument.getSentiment());
        return EmotionAnalysisDto.builder()
                .overallSentiment(textDocument.getSentiment())
                .positive(confidenceScores.getPositive())
                .neutral(confidenceScores.getNeutral())
                .negative(confidenceScores.getNegative())
                .userMessage(userMessage).build();
    }
}
