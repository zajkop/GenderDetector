package com.silenteight.genderdetector.controller;

import com.silenteight.genderdetector.algorithm.Gender;
import com.silenteight.genderdetector.controller.validator.GenderDetectorInputValidator;
import com.silenteight.genderdetector.controller.validator.ValidationStatus;
import com.silenteight.genderdetector.service.GenderDetectorService;
import com.silenteight.genderdetector.service.TokensProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Controller class provides endpoints for detecting gender and retrieving all tokens for each gender
 */
@RestController("/gender")
public class GenderDetectorController {

    private final GenderDetectorService genderDetectorService;
    private final TokensProviderService tokensProviderService;

    public GenderDetectorController(GenderDetectorService genderDetectorService, TokensProviderService tokensProviderService) {
        this.genderDetectorService = genderDetectorService;
        this.tokensProviderService = tokensProviderService;
    }

    /**
     * Gets detected gender using based on input wth token names and detector option
     * Before gender detection inputs are validated to meet the expectations
     *
     * @param name input with token names
     * @param detectorOption detector options ALL, SINGLE
     * @return detected gender
     */
    @GetMapping("/detect-gender")
    public ResponseEntity<String> getDetectedGender(@RequestParam String name, @RequestParam(name = "option") String detectorOption) {
        ValidationStatus validationStatus = new GenderDetectorInputValidator()
                .validateNameInput(name)
                .validateDetectorOptionInput(detectorOption)
                .validate();
        if (validationStatus.equals(ValidationStatus.FAILED)) {
            return new ResponseEntity<>("Wrong parameters format, name must contains tokens and detector option must be equal to SINGLE or ALL", HttpStatus.NOT_ACCEPTABLE);
        }
        Gender detectedGender = genderDetectorService.getDetectedGender(name, detectorOption);
        return new ResponseEntity<>(detectedGender.toString(), HttpStatus.OK);
    }

    /**
     * Gets all tokens for each gender using TokensProviderService
     * In case of failure return ResponseEntity with specified message and status
     *
     * @return all tokens for each gender
     */
    @GetMapping(value = "/all-genders-tokens", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<byte[]> getAllTokensForEachGender()  {
        try {
            return new ResponseEntity<>(tokensProviderService.getAllTokensForEachGender().readAllBytes(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Unable to read tokens from service".getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
