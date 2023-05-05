package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_tokens")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "token_id", nullable = false, unique = true))
public class UserToken extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "refresh_token", nullable = false, length = 500)
    private String refreshToken;
}