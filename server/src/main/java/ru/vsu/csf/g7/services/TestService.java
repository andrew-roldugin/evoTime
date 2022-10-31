package ru.csf.vsu.g7.evotime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TestService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Date getCurrDate() {
        return jdbcTemplate.queryForObject("select current_date", Date.class);
    }
}
