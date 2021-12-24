package com.pvlrs.spotifyrecommender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageAnalysisDto {

    private UUID id;
    private FaceAttributes faceAttributes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FaceAttributes {

        private EmotionAttribute emotion;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmotionAttribute {

        private Float anger;
        private Float contempt;
        private Float disgust;
        private Float fear;
        private Float happiness;
        private Float neutral;
        private Float sadness;
        private Float surprise;
    }
}
