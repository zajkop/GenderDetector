package com.silenteight.genderdetector.controller.validator;

import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.ALL_TOKENS_DETECTOR_OPTION;
import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.FIRST_TOKEN_DETECTOR_OPTION;

import java.util.ArrayList;
import java.util.List;

public class GenderDetectorInputValidator {

    private final List<ValidationStatus> validationResults;

    public GenderDetectorInputValidator() {
        validationResults = new ArrayList<>();
    }

    public GenderDetectorInputValidator validateNameInput (String input) {
        validationResults.add(validateBaseInputProperties(input));
        return this;
    }

    public GenderDetectorInputValidator validateDetectorOptionInput(String detectorOption) {
        if (validateBaseInputProperties(detectorOption).equals(ValidationStatus.FAILED) || !(detectorOption.equals(FIRST_TOKEN_DETECTOR_OPTION) || detectorOption.equals(ALL_TOKENS_DETECTOR_OPTION))) {
            validationResults.add(ValidationStatus.FAILED);
        }
        validationResults.add(ValidationStatus.PASSED);
        return this;
    }

    public ValidationStatus validate() {
        if (validationResults.contains(ValidationStatus.FAILED)) {
            return ValidationStatus.FAILED;
        }
        return ValidationStatus.PASSED;
    }

    private ValidationStatus validateBaseInputProperties(String input) {
        if (input.isEmpty() || input.isBlank() || !input.matches("^[a-zA-Z_ ]*$")) {
            return ValidationStatus.FAILED;
        }
        return ValidationStatus.PASSED;
    }
}
