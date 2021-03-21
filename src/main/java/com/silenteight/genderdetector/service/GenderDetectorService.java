package com.silenteight.genderdetector.service;

import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.FIRST_TOKEN_DETECTOR_OPTION;

import com.silenteight.genderdetector.algorithm.Gender;
import com.silenteight.genderdetector.algorithm.GenderDetector;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GenderDetectorService {

    private static final int FIRST_TOKEN = 0;
    private final GenderDetector genderDetector;

    public GenderDetectorService(GenderDetector genderDetector) {
        this.genderDetector = genderDetector;
    }

    public Gender getDetectedGender(String userInput, String detectionOption) {
        List<String> nameTokens = Arrays.asList(userInput.split(" "));
        if (detectionOption.equals(FIRST_TOKEN_DETECTOR_OPTION)) {
            String singleNameToken = nameTokens.get(FIRST_TOKEN);
            return genderDetector.detectGenderBasedOnSingleToken(singleNameToken);
        }
        return genderDetector.detectGenderBasedOnAllTokens(nameTokens);
    }
}
