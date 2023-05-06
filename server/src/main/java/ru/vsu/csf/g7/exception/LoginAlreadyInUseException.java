package ru.vsu.csf.g7.exception;

public class LoginAlreadyInUseException extends ApiException {
    public LoginAlreadyInUseException(String login) {
        super(String.format("Логин %s уже занят", login));
    }
}
