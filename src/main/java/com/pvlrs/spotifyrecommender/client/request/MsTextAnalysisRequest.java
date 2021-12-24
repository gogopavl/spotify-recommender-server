package com.pvlrs.spotifyrecommender.client.request;

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
public class MsTextAnalysisRequest {

    List<TextDocument> documents;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextDocument {

        private UUID id;
        private String text;
    }
}
