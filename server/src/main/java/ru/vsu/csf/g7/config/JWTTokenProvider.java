package ru.vsu.csf.g7.config;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.AbstractAuditable_;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.vsu.csf.g7.entity.User;

import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JWTTokenProvider {

    public enum TokenType {
        ACCESS,
        REFRESH
    }
    public String generateToken(User user, TokenType type) {
        Duration dt = null;
        String secret = null;

        switch (type) {
            case ACCESS -> {
                dt = SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME;
                secret = SecurityConstants.AT_SECRET;
            }
            case REFRESH -> {
                dt = SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME;
                secret = SecurityConstants.RT_SECRET;
            }
        }

        Instant now = Instant.now();
        Instant expiryDate = now.plus(dt);

        String userId = Long.toString(user.getId());
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("login", user.getLogin());
//        claimsMap.put("email", user.getEmail());
        claimsMap.put("role", user.getRole().name());

        ZoneId zoneId = ZoneId.systemDefault();

        return Jwts.builder()
                .setId(userId)
                .addClaims(claimsMap)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, TokenType type) {
        try {
            String secret =  switch (type) {
                case ACCESS ->  SecurityConstants.AT_SECRET;
                case REFRESH ->  SecurityConstants.RT_SECRET;
            };

            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException |
                 UnsupportedJwtException |
                 MalformedJwtException |
                 SignatureException | IllegalArgumentException e
        ) {
            log.error(e.getMessage());
            return false;
        }
    }

    public String getUserLoginFromToken(String token, TokenType type) {
        String secret =  switch (type) {
            case ACCESS ->  SecurityConstants.AT_SECRET;
            case REFRESH ->  SecurityConstants.RT_SECRET;
        };
        return (String) Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("login");
    }
}
