package com.silenteight.genderdetector.algorithm;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * Class provides tokens for each gender
 */
@Component
public class TokensProvider {

    private final TokenFileReader tokenFileReader;

    public TokensProvider(TokenFileReader tokenFileReader) {
        this.tokenFileReader = tokenFileReader;
    }

    /**
     * Provides all tokens for each gender using TokenFileReader class
     *
     * @return Tokens as InputStream
     */
    public InputStream provideAllTokensForEachGender() {
        return tokenFileReader.getTokensForEachGender();
    }
}
