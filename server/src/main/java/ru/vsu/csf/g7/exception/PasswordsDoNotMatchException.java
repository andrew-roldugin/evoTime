package ru.vsu.csf.g7.exception;

public class PasswordsDoNotMatchException extends ApiException {
    public PasswordsDoNotMatchException() {
        super("Вы ввели неверный пароль");
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
