package com.silenteight.genderdetector.algorithm;

import com.silenteight.genderdetector.utility.TokenFileReader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class TokensProvider {

    private final TokenFileReader tokenFileReader;

    public TokensProvider(TokenFileReader tokenFileReader) {
        this.tokenFileReader = tokenFileReader;
    }

    public InputStream provideAllTokensForEachGender() {
        return tokenFileReader.getTokensForEachGender();
    }
}
