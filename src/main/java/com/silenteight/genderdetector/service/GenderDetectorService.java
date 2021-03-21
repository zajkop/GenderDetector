package com.silenteight.genderdetector.service;

import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.FIRST_TOKEN_DETECTOR_OPTION;

import com.silenteight.genderdetector.algorithm.Gender;
import com.silenteight.genderdetector.algorithm.GenderDetector;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * GenderDetector service class
 */
@Service
public class GenderDetectorService {

    private static final int FIRST_TOKEN = 0;
    private final GenderDetector genderDetector;

    public GenderDetectorService(GenderDetector genderDetector) {
        this.genderDetector = genderDetector;
    }

    /**
     * Gets detected gender using GenderDetector based on given detection option
     * @param nameInput       name tokens
     * @param detectionOption detection option - ALL/SINGLE
     * @return detected gender
     */
    public Gender getDetectedGender(String nameInput, String detectionOption) {
        List<String> nameTokens = Arrays.asList(nameInput.split(" "));
        if (detectionOption.equals(FIRST_TOKEN_DETECTOR_OPTION)) {
            String singleNameToken = nameTokens.get(FIRST_TOKEN);
            return genderDetector.detectGenderBasedOnSingleToken(singleNameToken);
        }
        return genderDetector.detectGenderBasedOnAllTokens(nameTokens);
    }
}
