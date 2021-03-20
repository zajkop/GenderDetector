package com.silenteight.genderdetector.algorithm;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenderDetector {

    public Gender detectGenderBasedOnSingleToken(String token) {
        return Gender.INCONCLUSIVE;
    }

    public Gender detectGenderBasedOnAllTokens(List<String> tokens) {
        return Gender.INCONCLUSIVE;
    }

}
