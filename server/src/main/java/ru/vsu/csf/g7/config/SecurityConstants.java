package ru.vsu.csf.g7.config;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;
import java.util.Timer;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/auth/**";
    public static final String OPEN_URLS = "/api/test/**";

    public static final String SECRET = "SECRET_KEY";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final Duration ACCESS_TOKEN_EXPIRATION_TIME = Duration.of(1, ChronoUnit.HOURS);
    public static final Duration REFRESH_TOKEN_EXPIRATION_TIME = Duration.of(30, ChronoUnit.DAYS);
}
