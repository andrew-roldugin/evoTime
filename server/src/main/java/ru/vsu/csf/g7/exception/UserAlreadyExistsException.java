package ru.vsu.csf.g7.exception;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException(String login) {
        this("с логином", login);
    }
    public UserAlreadyExistsException(String field, String value) {
        super(String.format("Пользователь %s %s уже существует", field, value));
    }
}
