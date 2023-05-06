package ru.vsu.csf.g7.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
