package ru.vsu.csf.g7.payload.request;

import lombok.Data;
import ru.vsu.csf.g7.entity.ERole;
import ru.vsu.csf.g7.validations.annotations.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

    @Email(message = "Проверьте поле 'email'")
    @NotBlank(message = "Поле 'email' обязательно для заполнения")
    private String email;

    @NotEmpty(message = "Пожалуйста, заполните поле 'логин'")
    private String login;

    @NotEmpty(message = "Поле \"пароль\" обязательно для заполнения")
    @Size(min = 6, max = 30, message = "Длина пароля должна составлять от 6 до 30 символов")
    private String password;

    @NotEmpty(message = "Поле \"подтверждение пароля\" обязательно для заполнения")
    @Size(min = 6, max = 30, message = "Длина пароля должна составлять от 6 до 30 символов")
    private String confirmPassword;

    @NotEmpty(message = "Пожалуйста, заполните поле 'ФИО'")
    @Size(min = 3, message = "Длина поля должна составлять как минимум 3 символа")
    private String name;

    private ERole role = ERole.GUEST;
}
