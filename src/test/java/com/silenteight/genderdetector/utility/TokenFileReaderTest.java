package com.silenteight.genderdetector.utility;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TokenFileReader test class
 */
class TokenFileReaderTest {

    private static final String MALE_TOKENS_FILE = "tokens/male-test-tokens.txt";
    private static final String MALE_NAME = "Patryk";
    private static final String FEMALE_NAME = "Martyna";
    private static TokenFileReader tokenFileReader;

    /**
     * Creates TokenFileReader before tests execution
     */
    @BeforeAll
    public static void setUp() {
        tokenFileReader = new TokenFileReader();
    }

    /**
     * Verifies that token is present specified file
     */
    @Test
    public void verifyThatTokenIsInSpecifiedFile() {
        Assertions.assertThat(tokenFileReader.isTokenInSpecifiedFile(MALE_NAME, MALE_TOKENS_FILE))
                .as("Token %s should be found in file %s", MALE_NAME, MALE_TOKENS_FILE)
                .isTrue();
    }

    /**
     * Verifies that token is not present in specified file
     */
    @Test
    public void verifyThatTokenIsNotInSpecifiedFile() {
        Assertions.assertThat(tokenFileReader.isTokenInSpecifiedFile(FEMALE_NAME, MALE_TOKENS_FILE))
                .as("Token %s shouldn't be found in file %s", FEMALE_NAME, MALE_TOKENS_FILE)
                .isFalse();
    }

    /**
     * Verifies that corresponding to input tokens are present in specified file
     */
    @Test
    public void verifyThatCorrespondingTokensAreInSpecifiedFile() {
        List<String> inputTokens = List.of(MALE_NAME, FEMALE_NAME);
        List<String> correspondingTokensFromFile = tokenFileReader.getCorrespondingTokensInSpecifiedFile(inputTokens, MALE_TOKENS_FILE);
        Assertions.assertThat(correspondingTokensFromFile)
                .as("Only %s token should be found in file", MALE_NAME)
                .containsOnly(MALE_NAME);
    }

    /**
     * Checks if input stream is retrieved properly
     *
     * @throws IOException when can't get token's input stream
     */
    @Test
    public void shouldRetrieveTokensAsInputStream() throws IOException {
        InputStream tokensForEachGender = tokenFileReader.getTokensForEachGender();
        Assertions.assertThat(tokensForEachGender.readAllBytes()).as("Tokens stream should contains data").isNotEmpty();
    }
}