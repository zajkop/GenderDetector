package com.silenteight.genderdetector.algorithm;

import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.FEMALE_TOKEN_FILE_PATH;
import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.MALE_TOKEN_FILE_PATH;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

@Component
public class TokensProvider {

    private final TokenFileReader tokenFileReader;

    public TokensProvider(TokenFileReader tokenFileReader) {
        this.tokenFileReader = tokenFileReader;
    }

    public InputStreamResource provideAllTokensForGivenGender(Gender gender) {
        return tokenFileReader.getAllTokensFromFile(gender.equals(Gender.MALE) ? MALE_TOKEN_FILE_PATH : FEMALE_TOKEN_FILE_PATH);
    }
}
