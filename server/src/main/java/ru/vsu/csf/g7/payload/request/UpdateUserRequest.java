package ru.vsu.csf.g7.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateUserRequest {
    @NotEmpty(message = "Пожалуйста, введите логин")
    private String login;

    @NotEmpty(message = "Поле \"пароль\" должно быть заполнено")
    private String password;
}
