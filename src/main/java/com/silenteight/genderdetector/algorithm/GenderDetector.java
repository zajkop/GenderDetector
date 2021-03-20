package com.silenteight.genderdetector.algorithm;

import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.FEMALE_TOKEN_FILE_PATH;
import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.MALE_TOKEN_FILE_PATH;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenderDetector {

    private final TokenFileReader fileReader;

    public GenderDetector(TokenFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public Gender detectGenderBasedOnSingleToken(String token) {
        if (fileReader.isTokenInSpecifiedFile(token, MALE_TOKEN_FILE_PATH)) {
            return Gender.MALE;
        } else {
            if (fileReader.isTokenInSpecifiedFile(token, FEMALE_TOKEN_FILE_PATH)) {
                return Gender.FEMALE;
            }
        }
        return Gender.INCONCLUSIVE;
    }

    public Gender detectGenderBasedOnAllTokens(List<String> tokens) {
        int tokensHalfSize = tokens.size() / 2;
        List<String> maleTokens = fileReader.getCorrespondingTokensInSpecifiedFile(tokens, MALE_TOKEN_FILE_PATH);
        if (maleTokens.size() > tokensHalfSize) {
            return Gender.MALE;
        } else {
            List<String> femaleTokens = fileReader.getCorrespondingTokensInSpecifiedFile(tokens, FEMALE_TOKEN_FILE_PATH);
            if (femaleTokens.size() > tokensHalfSize) {
                return Gender.FEMALE;
            }
        }
        return Gender.INCONCLUSIVE;
    }
}
