package com.silenteight.genderdetector.service;

import com.silenteight.genderdetector.algorithm.TokensProvider;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * TokensProvider service class
 */
@Service
public class TokensProviderService {

    private final TokensProvider tokensProvider;

    public TokensProviderService(TokensProvider tokensProvider) {
        this.tokensProvider = tokensProvider;
    }

    /**
     * Gets all tokens for each gender using TokensProvider
     *
     * @return InputStream with provided tokens
     */
    public InputStream getAllTokensForEachGender() {
        return tokensProvider.provideAllTokensForEachGender();
    }
}
