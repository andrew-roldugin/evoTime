package ru.vsu.csf.g7.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Ничего не найдено");
    }
}
