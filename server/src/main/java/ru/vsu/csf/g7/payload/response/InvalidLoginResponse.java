package ru.vsu.csf.g7.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private String login;
    private String password;

    public InvalidLoginResponse() {
        this.login = "Неверный логин";
        this.password = "Неверный пароль";
    }
}
