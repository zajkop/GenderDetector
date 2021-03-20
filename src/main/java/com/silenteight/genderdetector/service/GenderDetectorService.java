package com.silenteight.genderdetector.service;

import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.FIRST_TOKEN_DETECTOR_OPTION;

import com.silenteight.genderdetector.algorithm.Gender;
import com.silenteight.genderdetector.algorithm.GenderDetector;
import com.silenteight.genderdetector.algorithm.TokensProvider;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GenderDetectorService {

    private static final int FIRST_TOKEN = 0;
    private final GenderDetector genderDetector;
    private final TokensProvider tokensProvider;

    public GenderDetectorService(GenderDetector genderDetector, TokensProvider tokensProvider) {
        this.genderDetector = genderDetector;
        this.tokensProvider = tokensProvider;
    }

    public Gender getDetectedGender(String userInput, String detectionOption) {
        List<String> nameTokens = Arrays.asList(userInput.split(" "));
        if (detectionOption.equals(FIRST_TOKEN_DETECTOR_OPTION)) {
            String singleNameToken = nameTokens.get(FIRST_TOKEN);
            return genderDetector.detectGenderBasedOnSingleToken(singleNameToken);
        }
        return genderDetector.detectGenderBasedOnAllTokens(nameTokens);
    }

    public InputStreamResource getAllTokensForGivenGender(Gender gender) {
          return tokensProvider.provideAllTokensForGivenGender(gender);
    }
}
