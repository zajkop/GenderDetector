package com.silenteight.genderdetector.algorithm;

import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.FEMALE_TOKEN_FILE_PATH;
import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.MALE_TOKEN_FILE_PATH;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class provides gender detection logic
 */
@Component
public class GenderDetector {

    private static final Logger LOGGER = LogManager.getLogger(GenderDetector.class);
    private final TokenFileReader fileReader;

    public GenderDetector(TokenFileReader fileReader) {
        this.fileReader = fileReader;
    }

    /**
     * Detects gender based on single name token by checking if token is in male or female tokens file
     * If token is not found in both files INCONCLUSIVE gender is returned
     *
     * Examples:
     * Token[Adam], Adam is present in male file - result MALE
     * Token[Martyna], Martyna is present in female file - result FEMALE
     * Token[Rokita], Rokita is not present in any file - result INCONCLUSIVE
     *
     * @param token name token
     * @return detected gender
     */
    public Gender detectGenderBasedOnSingleToken(String token) {
        if (fileReader.isTokenInSpecifiedFile(token, MALE_TOKEN_FILE_PATH)) {
            LOGGER.debug(String.format("Token %s is in male tokens file", token));
            return Gender.MALE;
        } else {
            if (fileReader.isTokenInSpecifiedFile(token, FEMALE_TOKEN_FILE_PATH)) {
                LOGGER.debug(String.format("Token %s is in female tokens file", token));
                return Gender.FEMALE;
            }
        }
        LOGGER.debug(String.format("Token %s is not present in any file, gender can't be detected", token));
        return Gender.INCONCLUSIVE;
    }

    /**
     * Detects gender based on every name token
     * To detect gender male or female tokens must be the majority in passed tokens
     *
     * Examples:
     * Tokens[Adam, Martyna], tokens are present in files - result INCONCLUSIVE because male/female tokens don't have majority
     * Tokens[Adam, Rokita], Adam is present in male file, but Rokita isn't - result INCONCLUSIVE because male tokens don't have majority
     * Tokens[Martyna, Rokita], Martyna is present in female file, but Rokita isn't - result INCONCLUSIVE because female tokens don't have majority
     * Tokens[Adam, Patryk, Martyna, Rokita], Adam and Patryk are present in male file, Martyna is in female file, Rokita isn't - result MALE because male tokens have majority
     *
     * @param tokens passed name tokens
     * @return detected gender
     */
    public Gender detectGenderBasedOnAllTokens(List<String> tokens) {
        int tokensHalfSize = tokens.size() / 2;
        List<String> maleTokens = fileReader.getCorrespondingTokensInSpecifiedFile(tokens, MALE_TOKEN_FILE_PATH);
        if (maleTokens.size() > tokensHalfSize) {
            LOGGER.debug("Male tokens are the majority tokens, MALE gender detected");
            return Gender.MALE;
        } else {
            List<String> femaleTokens = fileReader.getCorrespondingTokensInSpecifiedFile(tokens, FEMALE_TOKEN_FILE_PATH);
            if (femaleTokens.size() > tokensHalfSize) {
                LOGGER.debug("Female tokens are the majority tokens, FEMALE gender detected");
                return Gender.FEMALE;
            }
        }
        LOGGER.debug("Gender can't be detected based on given tokens:" + tokens);
        return Gender.INCONCLUSIVE;
    }
}
