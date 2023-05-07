package ru.vsu.csf.g7.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenResponse {
    private String accessToken;
    private String refreshToken;

    public JWTTokenResponse(String[] tokens) {
        this.accessToken = tokens[0];
        this.refreshToken = tokens[1];
    }
}
