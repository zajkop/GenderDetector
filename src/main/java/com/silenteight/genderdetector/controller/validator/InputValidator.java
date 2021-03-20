package com.silenteight.genderdetector.controller.validator;

import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.ALL_TOKENS_DETECTOR_OPTION;
import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.FIRST_TOKEN_DETECTOR_OPTION;

import org.springframework.stereotype.Component;

@Component
public class InputValidator {

    public ValidationStatus validate(String nameInput, String detectorOption) {
        ValidationStatus nameValidationStatus = validateNameInput(nameInput);
        ValidationStatus detectorOptionValidationStatus = validateDetectorOptionInput(detectorOption);
        if (nameValidationStatus.equals(ValidationStatus.PASSED) && detectorOptionValidationStatus.equals(ValidationStatus.PASSED)) {
            return ValidationStatus.PASSED;
        }
        return ValidationStatus.FAILED;
    }

    private ValidationStatus validateNameInput(String nameInput) {
        return validateBaseInputProperties(nameInput);
    }

    private ValidationStatus validateDetectorOptionInput(String detectorOption) {
        if (validateBaseInputProperties(detectorOption).equals(ValidationStatus.FAILED) || !(detectorOption.equals(FIRST_TOKEN_DETECTOR_OPTION) || detectorOption.equals(ALL_TOKENS_DETECTOR_OPTION))) {
            return ValidationStatus.FAILED;
        }
        return ValidationStatus.PASSED;
    }

    private ValidationStatus validateBaseInputProperties(String input) {
        if (input.isEmpty() || input.isBlank() || input.matches(".*\\d.*")) {
            return ValidationStatus.FAILED;
        }
        return ValidationStatus.PASSED;
    }
}
