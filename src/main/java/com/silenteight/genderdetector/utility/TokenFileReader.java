package com.silenteight.genderdetector.utility;

import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.FEMALE_TOKEN_FILE_PATH;
import static com.silenteight.genderdetector.utility.ProjectConstants.FilePaths.MALE_TOKEN_FILE_PATH;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class TokenFileReader {

    public boolean isTokenInSpecifiedFile(String token, String filePath) {
        Scanner scanner = new Scanner(getFileFromResourcesAsInputStream(filePath));
        while (scanner.hasNextLine()) {
            String tokenFromFile = scanner.nextLine();
            if (tokenFromFile.equals(token)) {
                return true;
            }
        }
        scanner.close();
        return false;
    }

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
        return foundedTokens;
    }

    public InputStream getTokensForEachGender() {
        List<InputStream> inputStreams = List.of(
                IOUtils.toInputStream("=====Male tokens=====\n", StandardCharsets.UTF_8),
                getFileFromResourcesAsInputStream(MALE_TOKEN_FILE_PATH),
                IOUtils.toInputStream("\n=====Female tokens=====\n", StandardCharsets.UTF_8),
                getFileFromResourcesAsInputStream(FEMALE_TOKEN_FILE_PATH)
        );
        return new SequenceInputStream(Collections.enumeration(inputStreams));
    }

    private InputStream getFileFromResourcesAsInputStream(String filePath) {
        return getClass()
                .getClassLoader()
                .getResourceAsStream(filePath);
    }
}
