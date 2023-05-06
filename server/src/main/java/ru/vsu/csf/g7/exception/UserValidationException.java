package ru.vsu.csf.g7.exception;

public class UserValidationException extends ApiException {
    public UserValidationException(String field) {
        super(String.format("Поле %s должно быть заполнено", field));
    }
}
