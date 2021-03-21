package com.silenteight.genderdetector.utility;

/**
 * Class provides commonly used constants classes in project
 */
public class ProjectConstants {

    private ProjectConstants() {}

    /**
     * Class provides file paths constants
     */
    public static class FilePaths {
        private FilePaths() {}
        public static final String MALE_TOKEN_FILE_PATH = "tokens/male-tokens.txt";
        public static final String FEMALE_TOKEN_FILE_PATH = "tokens/female-tokens.txt";
    }

    /**
     * Class provides detector options constants
     */
    public static class DetectorOptions {
        private DetectorOptions() {}
        public static final String FIRST_TOKEN_DETECTOR_OPTION = "SINGLE";
        public static final String ALL_TOKENS_DETECTOR_OPTION = "ALL";
    }
}
