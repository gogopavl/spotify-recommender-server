package com.pvlrs.spotifyrecommender.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmotionAnalysisUserMessageTemplates {

    private static final List<String> USER_MESSAGE_TEMPLATES = List.of(
            "Seems like you''re in a {0} mood. How about you try this...",
            "I can sense you''re {0}, so here you go!",
            "Feeling {0}, huh? Say no more...",
            "Hello Mr/Ms {0}. This is for you..."
    );

    public static String getRandomUserMessageTemplate() {
        return USER_MESSAGE_TEMPLATES.get(new Random().nextInt(USER_MESSAGE_TEMPLATES.size()));
    }
}
