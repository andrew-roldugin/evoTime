package ru.vsu.csf.g7.payload.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.vsu.csf.g7.entity.User;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Integer id;
    private String login;
    private String email;
    private String role;

    public static UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
