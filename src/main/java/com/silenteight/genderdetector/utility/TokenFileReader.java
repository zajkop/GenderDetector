package com.silenteight.genderdetector.utility;

import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.FEMALE_TOKEN_FILE_PATH;
import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.MALE_TOKEN_FILE_PATH;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class provides methods for tokens searching in specified files
 */
@Component
public class TokenFileReader {

    private static final Logger LOGGER = LogManager.getLogger(TokenFileReader.class);
    /**
     * Method looks for name token in specified file
     *
     * @param token    single name token
     * @param filePath path to file
     * @return true if token exists in specified file, otherwise return false
     */
    public boolean isTokenInSpecifiedFile(String token, String filePath) {
        Scanner scanner = new Scanner(getFileFromResourcesAsInputStream(filePath));
        while (scanner.hasNextLine()) {
            String tokenFromFile = scanner.nextLine();
            if (tokenFromFile.equals(token)) {
                return true;
            }
        }
        scanner.close();
        LOGGER.debug(String.format("Token %s not found in file: %s", token, filePath));
        return false;
    }

    /**
     * Method looks for tokens in specified file
     *
     * @param tokens name tokens
     * @param filePath path to file
     * @return tokens which are present in specified file
     */
    public List<String> getCorrespondingTokensInSpecifiedFile(List<String> tokens, String filePath) {
        List<String> foundedTokens = new ArrayList<>();
        Scanner scanner = new Scanner(getFileFromResourcesAsInputStream(filePath));
        while (scanner.hasNextLine()) {
            String token = scanner.nextLine();
            if (tokens.contains(token)) {
                foundedTokens.add(token);
            }
        }
        scanner.close();
        if (foundedTokens.isEmpty()) {
            LOGGER.debug("Tokens: " + tokens + " not found in file: " + filePath);
        }
        return foundedTokens;
    }

    /**
     * Gets tokens for each gender by combining file's InputStreams and male/female description lines
     *
     * @return tokens for each gender with male/female description lines as InputStream
     */
    public InputStream getTokensForEachGender() {
        List<InputStream> inputStreams = List.of(
                IOUtils.toInputStream("=====Male tokens=====\n", StandardCharsets.UTF_8),
                getFileFromResourcesAsInputStream(MALE_TOKEN_FILE_PATH),
                IOUtils.toInputStream("\n=====Female tokens=====\n", StandardCharsets.UTF_8),
                getFileFromResourcesAsInputStream(FEMALE_TOKEN_FILE_PATH)
        );
        return new SequenceInputStream(Collections.enumeration(inputStreams));
    }

    /**
     * Gets file from resources as InputStream
     *
     * @param filePath path to file
     * @return file's InputStream
     */
    private InputStream getFileFromResourcesAsInputStream(String filePath) {
        return getClass()
                .getClassLoader()
                .getResourceAsStream(filePath);
    }
}
