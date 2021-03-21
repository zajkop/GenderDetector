package com.silenteight.genderdetector.algorithm;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * GenderDetector test class
 */
class GenderDetectorTest {

    private static final String MALE_TOKEN = "Patryk";
    private static final String SECOND_MALE_TOKEN = "Zbigniew";
    private static final String FEMALE_TOKEN = "Martyna";
    private static final String SECOND_FEMALE_TOKEN = "Marta";
    private static final String INCONCLUSIVE_TOKEN = "Rokita";
    private static GenderDetector genderDetector;

    /**
     * Creates gender detector instance before tests execution
     */
    @BeforeAll
    public static void setUp() {
        genderDetector = new GenderDetector(new TokenFileReader());
    }

    /**
     * Verifies that detector returns male gender based on single token
     */
    @Test
    void verifyThatDetectorReturnsMaleGenderBasedOnMaleSingleToken() {
        Gender detectedGender = genderDetector.detectGenderBasedOnSingleToken(MALE_TOKEN);
        Assertions.assertThat(detectedGender)
                .as("For given token %s should detect male gender", MALE_TOKEN)
                .isEqualTo(Gender.MALE);
    }

    /**
     * Verifies that detector returns female gender based on single token
     */
    @Test
    void verifyThatDetectorReturnsFemaleGenderBasedOnFemaleSingleToken() {
        Gender detectedGender = genderDetector.detectGenderBasedOnSingleToken(FEMALE_TOKEN);
        Assertions.assertThat(detectedGender)
                .as("For given token %s should detect female gender", FEMALE_TOKEN)
                .isEqualTo(Gender.FEMALE);
    }

    /**
     * Verifies that detector returns inconclusive gender based on single token
     */
    @Test
    void verifyThatDetectorReturnsInconclusiveGenderBasedOnUnknownSingleToken() {
        Gender detectedGender = genderDetector.detectGenderBasedOnSingleToken(INCONCLUSIVE_TOKEN);
        Assertions.assertThat(detectedGender)
                .as("For given token %s should detect inconclusive gender", INCONCLUSIVE_TOKEN)
                .isEqualTo(Gender.INCONCLUSIVE);
    }

    @Test
    void verifyThatDetectorReturnsMaleGenderBasedOnAllTokens() {
        List<String> inputTokens = List.of(MALE_TOKEN, FEMALE_TOKEN, SECOND_MALE_TOKEN);
        Gender detectedGender = genderDetector.detectGenderBasedOnAllTokens(inputTokens);
        Assertions.assertThat(detectedGender)
                .as("For given tokens %s detector should detect male gender", inputTokens)
                .isEqualTo(Gender.MALE);
    }

    @Test
    void verifyThatDetectorReturnsFemaleGenderBasedOnAllTokens() {
        List<String> inputTokens = List.of(MALE_TOKEN, FEMALE_TOKEN, SECOND_FEMALE_TOKEN);
        Gender detectedGender = genderDetector.detectGenderBasedOnAllTokens(inputTokens);
        Assertions.assertThat(detectedGender)
                .as("For given tokens %s detector should detect female gender", inputTokens)
                .isEqualTo(Gender.FEMALE);
    }

    @Test
    void verifyThatDetectorReturnsInconclusiveGenderBasedOnAllTokens() {
        List<String> inputTokens = List.of(MALE_TOKEN, FEMALE_TOKEN, INCONCLUSIVE_TOKEN);
        Gender detectedGender = genderDetector.detectGenderBasedOnAllTokens(inputTokens);
        Assertions.assertThat(detectedGender)
                .as("For given tokens %s detector should detect inconclusive gender", inputTokens)
                .isEqualTo(Gender.INCONCLUSIVE);
    }
}