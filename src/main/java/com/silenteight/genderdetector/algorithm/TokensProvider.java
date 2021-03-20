package com.silenteight.genderdetector.algorithm;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

@Component
public class TokensProvider {

    private static final String MALE_TOKEN_FILE_PATH = "tokens/male-tokens.txt";
    private static final String FEMALE_TOKEN_FILE_PATH = "tokens/female-tokens.txt";
    private final TokenFileReader tokenFileReader;

    public TokensProvider(TokenFileReader tokenFileReader) {
        this.tokenFileReader = tokenFileReader;
    }

    public InputStreamResource provideAllTokensForGivenGender(Gender gender) {
        return tokenFileReader.getAllTokensFromFile(gender.equals(Gender.MALE) ? MALE_TOKEN_FILE_PATH : FEMALE_TOKEN_FILE_PATH);
    }
}
