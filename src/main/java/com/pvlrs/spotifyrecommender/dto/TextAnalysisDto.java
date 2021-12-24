package com.pvlrs.spotifyrecommender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextAnalysisDto {

    private List<TextDocument> documents;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextDocument {

        private UUID id;
        private String sentiment;
        private ConfidenceScores confidenceScores;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConfidenceScores {

        private Float positive;
        private Float neutral;
        private Float negative;
    }
}
