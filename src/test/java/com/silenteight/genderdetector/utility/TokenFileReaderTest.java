package com.silenteight.genderdetector.utility;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class TokenFileReaderTest {

    private static final String MALE_TOKENS_FILE = "tokens/male-test-tokens.txt";
    private static final String MALE_NAME = "Patryk";
    private static final String FEMALE_NAME = "Martyna";
    private static TokenFileReader tokenFileReader;

    @BeforeAll
    public static void setUp() {
        tokenFileReader = new TokenFileReader();
    }

    @Test
    public void verifyThatTokenIsInSpecifiedFile() {
        Assertions.assertThat(tokenFileReader.isTokenInSpecifiedFile(MALE_NAME, MALE_TOKENS_FILE))
                .as("Token %s should be found in file %s", MALE_NAME, MALE_TOKENS_FILE)
                .isTrue();
    }

    @Test
    public void verifyThatTokenIsNotInSpecifiedFile() {
        Assertions.assertThat(tokenFileReader.isTokenInSpecifiedFile(FEMALE_NAME, MALE_TOKENS_FILE))
                .as("Token %s shouldn't be found in file %s", FEMALE_NAME, MALE_TOKENS_FILE)
                .isFalse();
    }

    @Test
    public void verifyThatCorrespondingTokensAreInSpecifiedFile() {
        List<String> inputTokens = List.of(MALE_NAME, FEMALE_NAME);
        List<String> correspondingTokensFromFile = tokenFileReader.getCorrespondingTokensInSpecifiedFile(inputTokens, MALE_TOKENS_FILE);
        Assertions.assertThat(correspondingTokensFromFile)
                .as("Only %s token should be found in file", MALE_NAME)
                .containsOnly(MALE_NAME);
    }
}