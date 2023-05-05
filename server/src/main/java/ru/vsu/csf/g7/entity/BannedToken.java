package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import ru.vsu.csf.g7.config.SecurityConstants;

import java.time.Instant;

@Entity
@Table(name = "banned_tokens")
public class BannedToken {
    @Id()
    @Column(name = "banned_token_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "delete_after")
    private Instant deleteAfter;

}
