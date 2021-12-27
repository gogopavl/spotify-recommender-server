package com.pvlrs.spotifyrecommender.enums;

import lombok.Getter;

public enum BasicEmotion {

    POSITIVE("positive"),
    NEUTRAL("neutral"),
    NEGATIVE("negative");

    @Getter
    private final String value;

    BasicEmotion(String value) {
        this.value = value;
    }

    public static BasicEmotion fromString(String emotion) {
        return BasicEmotion.valueOf(emotion.toUpperCase());
    }
}
