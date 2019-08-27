package com.example.continuousdelivery.util;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable login
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private Constants() {
    }
}