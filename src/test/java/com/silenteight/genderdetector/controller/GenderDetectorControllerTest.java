package com.silenteight.genderdetector.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 * GenderDetectorController test class
 */
@SpringBootTest()
public class GenderDetectorControllerTest {

    private static final String INCONCLUSIVE = "INCONCLUSIVE";
    private static final String FEMALE = "FEMALE";
    private static final String MALE = "MALE";

    @Autowired
    private GenderDetectorController genderDetectorController;

    /**
     * Verifies that detected gender is equal to male based on all tokens
     */
    @Test
    public void verifyThatMaleGenderIsReturnedBasedOnAllTokens() {
        ResponseEntity<String> detectedGender = genderDetectorController.getDetectedGender("Patryk Martyna Adam", "ALL");
        Assertions.assertThat(detectedGender.getBody())
                .as("Detected gender should be equal to male based on all tokens")
                .isEqualTo(MALE);
    }

    /**
     * Verifies that detected gender is equal to female based on all tokens
     */
    @Test
    public void verifyThatFemaleGenderIsReturnedBasedOnAllTokens() {
        ResponseEntity<String> detectedGender = genderDetectorController.getDetectedGender("Agnieszka Martyna Adam", "ALL");
        Assertions.assertThat(detectedGender.getBody())
                .as("Detected gender should be equal to female based on all tokens")
                .isEqualTo(FEMALE);
    }

    /**
     * Verifies that detected gender is equal to inconclusive based on all tokens
     */
    @Test
    public void verifyThatInconclusiveGenderIsReturnedBasedOnAllTokens() {
        ResponseEntity<String> detectedGender = genderDetectorController.getDetectedGender("Rokita Martyna Adam", "ALL");
        Assertions.assertThat(detectedGender.getBody())
                .as("Detected gender should be equal to inconclusive based on all tokens")
                .isEqualTo(INCONCLUSIVE);
    }

    /**
     * Verifies that detected gender is equal to male based on single token
     */
    @Test
    public void verifyThatMaleGenderIsReturnedBasedOnSingleToken() {
        ResponseEntity<String> detectedGender = genderDetectorController.getDetectedGender("Patryk Martyna Agnieszka", "SINGLE");
        Assertions.assertThat(detectedGender.getBody())
                .as("Detected gender should be equal to male based on single token")
                .isEqualTo(MALE);
    }

    /**
     * Verifies that detected gender is equal to female based on single token
     */
    @Test
    public void verifyThatFemaleGenderIsReturnedBasedOnSingleToken() {
        ResponseEntity<String> detectedGender = genderDetectorController.getDetectedGender("Martyna Patryk Adam", "SINGLE");
        Assertions.assertThat(detectedGender.getBody())
                .as("Detected gender should be equal to female based on single token")
                .isEqualTo(FEMALE);
    }

    /**
     * Verifies that detected gender is equal to inconclusive based on single token
     */
    @Test
    public void verifyThatInconclusiveGenderIsReturnedBasedOnSingleToken() {
        ResponseEntity<String> detectedGender = genderDetectorController.getDetectedGender("Rokita Martyna Agnieszka", "SINGLE");
        Assertions.assertThat(detectedGender.getBody())
                .as("Detected gender should be equal to inconclusive based on single token")
                .isEqualTo(INCONCLUSIVE);
    }

    /**
     * Verifies that error message is returned when parameters validation fails
     */
    @Test
    public void verifyThatErrorMessageIsReturnedWhenParametersValidationFails() {
        ResponseEntity<String> errorResponse = genderDetectorController.getDetectedGender("Patryk Martyna21 Agnieszka", "SINGLE");
        Assertions.assertThat(errorResponse.getBody())
                .as("Error should occur when parameters validation fails")
                .contains("Wrong parameters format");
    }

    /**
     * Verifies that male/female tokens are returned
     */
    @Test
    public void verifyThatTokensAreReturnedForEachGender() {
        ResponseEntity<byte[]> responseBytes = genderDetectorController.getAllTokensForEachGender();
        Assertions.assertThat(responseBytes.getBody())
                .as("Byte response should not be null")
                .isNotNull();
        Assertions.assertThat(new String(responseBytes.getBody()))
                .as("Byte response should contains male and female tokens")
                .contains("Patryk", "Agnieszka");
    }
}