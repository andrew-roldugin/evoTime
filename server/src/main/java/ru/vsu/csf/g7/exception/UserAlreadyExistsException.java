package ru.vsu.csf.g7.exception;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException(String login) {
        super(String.format("Пользователь с логином %s уже существует", login));
    }
}
