package ru.vsu.csf.g7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TestService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Instant getCurrDateTime() {
        return jdbcTemplate.queryForObject("select current_timestamp", Instant.class);
    }
}
