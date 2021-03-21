package com.silenteight.genderdetector.controller.validator;

import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.ALL_TOKENS_DETECTOR_OPTION;
import static com.silenteight.genderdetector.utility.ProjectConstants.DetectorOptions.FIRST_TOKEN_DETECTOR_OPTION;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provides methods responsible for validating gender detector's inputs
 */
public class GenderDetectorInputValidator {

    private static final Logger LOGGER = LogManager.getLogger(GenderDetectorInputValidator.class);
    private final List<ValidationStatus> validationResults;

    public GenderDetectorInputValidator() {
        validationResults = new ArrayList<>();
    }

    /**
     * Validates name input by checking base input properties, status is added to validation results
     *
     * @param input name input
     * @return GenderDetectorInputValidator instance
     */
    public GenderDetectorInputValidator validateNameInput (String input) {
        validationResults.add(validateBaseInputProperties(input));
        return this;
    }

    /**
     * Validates detector option input as it should pass base input validation and be equals to SINGLE or ALL
     * Detector option validation status is added to validation results
     *
     * @param input detector option input
     * @return GenderDetectorInputValidator instance
     */
    public GenderDetectorInputValidator validateDetectorOptionInput(String input) {
        if (validateBaseInputProperties(input).equals(ValidationStatus.FAILED) || !(input.equals(FIRST_TOKEN_DETECTOR_OPTION) || input.equals(ALL_TOKENS_DETECTOR_OPTION))) {
            LOGGER.error("Detector option input validation fails for input: " + input);
            validationResults.add(ValidationStatus.FAILED);
        }
        validationResults.add(ValidationStatus.PASSED);
        return this;
    }

    /**
     * Validates input by checking if validation results contains FAILED status
     * if it's input validation failed and validation status FAILED is returned
     * otherwise validation passed and validation status PASSED is returned
     *
     * @return validation status
     */
    public ValidationStatus validate() {
        if (validationResults.contains(ValidationStatus.FAILED)) {
            LOGGER.error("Validation results contains FAILED validation status, input validation fails");
            return ValidationStatus.FAILED;
        }
        return ValidationStatus.PASSED;
    }

    /**
     * Validates base input properties:
     * - empty input
     * - blank input
     * - contains only letters and spaces
     * if validation fails return validation status FAILED
     * otherwise return validation status PASSED
     *
     * @param input input
     * @return validation status
     */
    private ValidationStatus validateBaseInputProperties(String input) {
        if (input.isEmpty() || input.isBlank() || !input.matches("^[a-zA-Z_ ]*$")) {
            LOGGER.error("Base input validation fails for input: " + input);
            return ValidationStatus.FAILED;
        }
        return ValidationStatus.PASSED;
    }
}
