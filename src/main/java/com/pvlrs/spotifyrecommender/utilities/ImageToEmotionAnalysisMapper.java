package com.pvlrs.spotifyrecommender.utilities;

import com.pvlrs.spotifyrecommender.dto.EmotionAnalysisDto;
import com.pvlrs.spotifyrecommender.dto.EmotionAnalysisDto.EmotionAnalysisDtoBuilder;
import com.pvlrs.spotifyrecommender.dto.ImageAnalysisDto;
import com.pvlrs.spotifyrecommender.dto.ImageAnalysisDto.EmotionAttribute;
import com.pvlrs.spotifyrecommender.enums.BasicEmotion;
import com.pvlrs.spotifyrecommender.enums.ImageEmotion;
import com.pvlrs.spotifyrecommender.exception.AnalysisException;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static com.pvlrs.spotifyrecommender.constants.EmotionAnalysisUserMessageTemplates.getRandomUserMessageTemplate;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.ANGER;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.CONTEMPT;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.DISGUST;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.FEAR;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.HAPPINESS;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.NEUTRAL;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.SADNESS;
import static com.pvlrs.spotifyrecommender.enums.ImageEmotion.SURPRISE;

@UtilityClass
public class ImageToEmotionAnalysisMapper {

    private static final Float NEGATIVE_UPPER_LIMIT = -0.33F;
    private static final Float NEUTRAL_UPPER_LIMIT = 0.33F;

    private static final Set<ImageEmotion> positiveEmotions = Set.of(HAPPINESS);
    private static final Set<ImageEmotion> neutralEmotions = Set.of(NEUTRAL, SURPRISE);
    private static final Set<ImageEmotion> negativeEmotions = Set.of(ANGER, CONTEMPT, DISGUST, FEAR, SADNESS);

    public static EmotionAnalysisDto toEmotionAnalysis(ImageAnalysisDto imageAnalysisDto) {

        AtomicReference<Float> positiveEmotionScore = new AtomicReference<>(0.0F);
        AtomicReference<Float> neutralEmotionScore = new AtomicReference<>(0.0F);
        AtomicReference<Float> negativeEmotionScore = new AtomicReference<>(0.0F);
        calculateBasicEmotionScores(imageAnalysisDto, positiveEmotionScore, neutralEmotionScore, negativeEmotionScore);

        EmotionAnalysisDtoBuilder emotionAnalysisBuilder = EmotionAnalysisDto.builder();

        float overallSentimentScore = positiveEmotionScore.get() - negativeEmotionScore.get();
        setOverallSentiment(emotionAnalysisBuilder, overallSentimentScore);

        EmotionAnalysisDto emotionAnalysisDto = buildInstance(emotionAnalysisBuilder, imageAnalysisDto,
                positiveEmotionScore, negativeEmotionScore);
        emotionAnalysisDto.setUserMessage(MessageFormat.format(getRandomUserMessageTemplate(),
                emotionAnalysisDto.getOverallSentiment()));
        return emotionAnalysisDto;
    }

    private static void calculateBasicEmotionScores(ImageAnalysisDto imageAnalysisDto,
                                                    AtomicReference<Float> positiveEmotionScore,
                                                    AtomicReference<Float> neutralEmotionScore,
                                                    AtomicReference<Float> negativeEmotionScore) {
        EmotionAttribute emotionAttributes = imageAnalysisDto.getFaceAttributes().getEmotion();
        Field[] emotionAttributeFields = emotionAttributes.getClass().getDeclaredFields();
        Arrays.stream(emotionAttributeFields).forEach(emotion -> {
            try {
                emotion.setAccessible(true);
                // valueOf is case-sensitive
                ImageEmotion imageEmotion = ImageEmotion.fromString(emotion.getName());
                Float emotionValue = (Float) emotion.get(emotionAttributes);
                if (negativeEmotions.contains(imageEmotion)) {
                    negativeEmotionScore.updateAndGet(value -> value + emotionValue);
                } else if (neutralEmotions.contains(imageEmotion)) {
                    neutralEmotionScore.updateAndGet(value -> value + emotionValue);
                } else if (positiveEmotions.contains(imageEmotion)) {
                    positiveEmotionScore.updateAndGet(value -> value + emotionValue);
                }
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
                throw new AnalysisException("Could not map image emotions to basic emotions.");
            }
        });
    }

    private static void setOverallSentiment(EmotionAnalysisDtoBuilder emotionAnalysisBuilder,
                                            float overallSentimentScore) {
        if (overallSentimentScore < NEGATIVE_UPPER_LIMIT) {
            emotionAnalysisBuilder.overallSentiment(BasicEmotion.NEGATIVE.getValue());
        } else if (overallSentimentScore < NEUTRAL_UPPER_LIMIT) {
            emotionAnalysisBuilder.overallSentiment(BasicEmotion.NEUTRAL.getValue());
        } else {
            emotionAnalysisBuilder.overallSentiment(BasicEmotion.POSITIVE.getValue());
        }
    }

    private static EmotionAnalysisDto buildInstance(EmotionAnalysisDtoBuilder emotionAnalysisBuilder,
                                                    ImageAnalysisDto imageAnalysisDto,
                                                    AtomicReference<Float> positiveEmotionScore,
                                                    AtomicReference<Float> negativeEmotionScore) {
        EmotionAttribute emotionAttributes = imageAnalysisDto.getFaceAttributes().getEmotion();
        return emotionAnalysisBuilder
                // Image-specific emotions
                .anger(emotionAttributes.getAnger())
                .contempt(emotionAttributes.getContempt())
                .disgust(emotionAttributes.getDisgust())
                .fear(emotionAttributes.getFear())
                .happiness(emotionAttributes.getHappiness())
                .sadness(emotionAttributes.getSadness())
                .surprise(emotionAttributes.getSurprise())
                // Basic emotions
                .neutral(emotionAttributes.getNeutral())
                .negative(negativeEmotionScore.get())
                .positive(positiveEmotionScore.get()).build();
    }
}
