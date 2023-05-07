package ru.vsu.csf.g7.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RefreshTokenRequest {
    @NotEmpty
    private String refreshToken;
}
