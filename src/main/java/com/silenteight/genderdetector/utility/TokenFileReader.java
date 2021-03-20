package com.silenteight.genderdetector.utility;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
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

    private InputStream getFileFromResourcesAsInputStream(String filePath) {
        return getClass()
                .getClassLoader()
                .getResourceAsStream(filePath);
    }

}
