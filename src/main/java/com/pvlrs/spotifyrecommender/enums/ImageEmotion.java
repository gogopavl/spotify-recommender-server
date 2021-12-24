package com.pvlrs.spotifyrecommender.enums;

import lombok.Getter;

public enum ImageEmotion {

    ANGER("anger"),
    CONTEMPT("contempt"),
    DISGUST("disgust"),
    FEAR("fear"),
    HAPPINESS("happiness"),
    NEUTRAL("neutral"),
    SADNESS("sadness"),
    SURPRISE("surprise");

    @Getter
    private final String value;

    ImageEmotion(String value) {
        this.value = value;
    }
}
