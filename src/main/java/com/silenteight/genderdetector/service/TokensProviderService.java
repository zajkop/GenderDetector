package com.silenteight.genderdetector.service;

import com.silenteight.genderdetector.algorithm.TokensProvider;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class TokensProviderService {

    private final TokensProvider tokensProvider;

    public TokensProviderService(TokensProvider tokensProvider) {
        this.tokensProvider = tokensProvider;
    }

    public InputStream getAllTokensForEachGender() {
        return tokensProvider.provideAllTokensForEachGender();
    }
}
