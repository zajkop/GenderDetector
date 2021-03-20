package com.silenteight.genderdetector.controller;

import com.silenteight.genderdetector.algorithm.Gender;
import com.silenteight.genderdetector.controller.validator.InputValidator;
import com.silenteight.genderdetector.controller.validator.ValidationStatus;
import com.silenteight.genderdetector.service.GenderDetectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class GenderDetectorController {

    private final GenderDetectorService genderDetectorService;
    private final InputValidator validator;

    public GenderDetectorController(GenderDetectorService genderDetectorService, InputValidator validator) {
        this.genderDetectorService = genderDetectorService;
        this.validator = validator;
    }

    @GetMapping("/detect-gender")
    public ResponseEntity<String> getDetectedGender(@RequestParam String name, @RequestParam(name = "option") String detectorOption) {
        ValidationStatus validationStatus = validator.validate(name, detectorOption);
        if (validationStatus.equals(ValidationStatus.FAILED)) {
            return new ResponseEntity<>("Parameters validation failed", HttpStatus.NOT_ACCEPTABLE);
        }
        Gender detectedGender = genderDetectorService.getDetectedGender(name, detectorOption);
        return new ResponseEntity<>(detectedGender.toString(), HttpStatus.OK);
    }

    @GetMapping("/all-gender-tokens")
    public void getAllGenderTokens() {

    }
}
