package com.silenteight.genderdetector.service;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Token provider service class
 */
@Service
public class TokensProviderService {

    private final TokenFileReader tokenFileReader;

    public TokensProviderService(TokenFileReader tokenFileReader) {
        this.tokenFileReader = tokenFileReader;
    }

    /**
     * Provides all tokens for each gender using TokenFileReader class
     *
     * @return available tokens as byte array
     */
    public byte[] provideAllTokensForEachGender() throws IOException {
        return tokenFileReader.getTokensForEachGender().readAllBytes();
    }
}
