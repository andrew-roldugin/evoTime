package ru.vsu.csf.g7.exception;

public class UserNotAuthorizedException extends ApiException {
    public UserNotAuthorizedException() {
        super("Пользователь не авторизован");
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }

}
