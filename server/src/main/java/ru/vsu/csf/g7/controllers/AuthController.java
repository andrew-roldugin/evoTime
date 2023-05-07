package ru.vsu.csf.g7.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.g7.config.JWTTokenProvider;
import ru.vsu.csf.g7.config.SecurityConstants;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.entity.UserToken;
import ru.vsu.csf.g7.payload.request.LoginRequest;
import ru.vsu.csf.g7.payload.request.RefreshTokenRequest;
import ru.vsu.csf.g7.payload.request.SignupRequest;
import ru.vsu.csf.g7.payload.response.JWTTokenResponse;
import ru.vsu.csf.g7.payload.response.MessageResponse;
import ru.vsu.csf.g7.services.TokenService;
import ru.vsu.csf.g7.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
@AllArgsConstructor
public class AuthController {

    private final JWTTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new JWTTokenResponse(doLogin(loginRequest)));
    }

    private String[] doLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new String[]{
                SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(((User) authentication.getPrincipal()), JWTTokenProvider.TokenType.ACCESS),
                tokenService.createRefreshToken(loginRequest.getLogin(), loginRequest.getFingerprint()).getRefreshToken()
        };
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            final User user = userService.createUser(signupRequest);
            return ResponseEntity.ok().body(new MessageResponse("Пользователь успешно создан"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshToken(@Valid @RequestBody RefreshTokenRequest req) {
        String refreshToken = req.getRefreshToken();
        Optional<UserToken> optionalUserToken = tokenService.findByToken(refreshToken);
        if (optionalUserToken.isPresent()) {
            if (jwtTokenProvider.validateToken(refreshToken, JWTTokenProvider.TokenType.REFRESH)
                    && optionalUserToken.get().getRefreshToken().equals(refreshToken)) {
                return ResponseEntity.ok(new JWTTokenResponse(new String[]{
                        SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(optionalUserToken.get().getUser(), JWTTokenProvider.TokenType.ACCESS),
                        refreshToken
                }));
            }
        }
        return ResponseEntity.internalServerError().body("Токен устарел");
    }
}
