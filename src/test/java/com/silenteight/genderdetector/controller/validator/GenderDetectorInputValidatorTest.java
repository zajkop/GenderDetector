package com.silenteight.genderdetector.controller.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * GenderDetectorInputValidator test class
 */
class GenderDetectorInputValidatorTest {

    private GenderDetectorInputValidator validator;

    /**
     * Creates new GenderDetectorInputValidator before each test execution
     */
    @BeforeEach()
    public void setUp() {
        validator = new GenderDetectorInputValidator();
    }

    /**
     * Verifies that validation pass on correct name input
     */
    @Test
    public void verifyThatValidationPassOnCorrectNameInput() {
        String nameInput = "Patryk Agnieszka Rokita";
        Assertions.assertThat(validator
                .validateNameInput(nameInput)
                .validate())
                .as("Validation for input %s should pass", nameInput)
                .isEqualTo(ValidationStatus.PASSED);
    }

    /**
     * Verifies that validation fails on wrong name input
     */
    @Test
    public void verifyThatValidationFailsOnWrongNameInput() {
        String nameInput = "Patryk A21 %91Marek";
        Assertions.assertThat(validator
                .validateNameInput(nameInput)
                .validate())
                .as("Validation for input %s should fail", nameInput)
                .isEqualTo(ValidationStatus.FAILED);
    }

    /**
     * Verifies that validation pass on correct detector option input
     */
    @Test
    public void verifyThatValidationPassOnCorrectDetectorOptionInput() {
        String detectorOptionInput = "SINGLE";
        Assertions.assertThat(validator
                .validateDetectorOptionInput(detectorOptionInput)
                .validate())
                .as("Validation for input %s should pass", detectorOptionInput)
                .isEqualTo(ValidationStatus.PASSED);
    }

    /**
     * Verifies that validation fails on wrong detector option input
     */
    @Test
    public void verifyThatValidationFailsOnWrongDetectorOptionInput() {
        String detectorOptionInput = "ANY";
        Assertions.assertThat(validator
                .validateDetectorOptionInput(detectorOptionInput)
                .validate())
                .as("Validation for input %s should fail", detectorOptionInput)
                .isEqualTo(ValidationStatus.FAILED);
    }

    /**
     * Verify that validation pass on correct detector option and correct name input
     */
    @Test
    public void verifyThatValidationPassOnCorrectDetectorOptionAndNameInputs() {
        String nameInput = "Patryk Martyna";
        String detectorOptionInput = "ALL";
        Assertions.assertThat(validator
                .validateNameInput(nameInput)
                .validateDetectorOptionInput(detectorOptionInput)
                .validate())
                .as("Validation for name input %s and detector option input %s should pass", nameInput, detectorOptionInput)
                .isEqualTo(ValidationStatus.PASSED);
    }

    /**
     * Verify that validation fails on wrong detector option and wrong name input
     */
    @Test
    public void verifyThatValidationFailsOnWrongDetectorOptionAndNameInputs() {
        String nameInput = "Patryk %541";
        String detectorOptionInput = "ANYF98";
        Assertions.assertThat(validator
                .validateNameInput(nameInput)
                .validateDetectorOptionInput(detectorOptionInput)
                .validate())
                .as("Validation for name input %s and detector option input %s should fail", nameInput, detectorOptionInput)
                .isEqualTo(ValidationStatus.FAILED);
    }

    /**
     * Verify that validation fails on correct detector option and wrong name input
     */
    @Test
    public void verifyThatValidationFailsOnCorrectDetectorOptionAndWrongNameInput() {
        String nameInput = "Patryk Martyna21";
        String detectorOptionInput = "ALL";
        Assertions.assertThat(validator
                .validateNameInput(nameInput)
                .validateDetectorOptionInput(detectorOptionInput)
                .validate())
                .as("Validation for name input %s and detector option input %s should fail", nameInput, detectorOptionInput)
                .isEqualTo(ValidationStatus.FAILED);
    }

    /**
     * Verify that validation fails on wrong detector option and correct name input
     */
    @Test
    public void verifyThatValidationFailsOnWrongDetectorOptionAndCorrectNameInput() {
        String nameInput = "Patryk Martyna";
        String detectorOptionInput = "ANY56";
        Assertions.assertThat(validator
                .validateNameInput(nameInput)
                .validateDetectorOptionInput(detectorOptionInput)
                .validate())
                .as("Validation for name input %s and detector option input %s should fail", nameInput, detectorOptionInput)
                .isEqualTo(ValidationStatus.FAILED);
    }
}