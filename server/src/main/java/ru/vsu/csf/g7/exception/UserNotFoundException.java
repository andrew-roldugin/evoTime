package ru.vsu.csf.g7.exception;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super("Пользователь не найден");
    }

    public UserNotFoundException(String byField, String value) {
        super(String.format("Пользователь %s %s не найден", byField, value));
    }

    public UserNotFoundException(String login) {
        this("с логином", login);
    }
}
