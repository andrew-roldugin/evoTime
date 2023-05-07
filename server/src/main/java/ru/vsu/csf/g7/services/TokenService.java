package ru.vsu.csf.g7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.csf.g7.config.JWTTokenProvider;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.entity.UserToken;
import ru.vsu.csf.g7.exception.UserNotFoundException;
import ru.vsu.csf.g7.repos.UserRepository;
import ru.vsu.csf.g7.repos.UserTokenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private UserTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserToken> findByToken(String token) {
        return tokenRepository.findByRefreshToken(token);
    }

    public UserToken createRefreshToken(String login, String fingerprint) {
        final Optional<User> optionalUser = userRepository.findUserByLogin(login);

        if (optionalUser.isPresent()) {
            final Optional<UserToken> userToken = tokenRepository.findByUserIdAndFingerprint(optionalUser.get().getId(), fingerprint);
            if (userToken.isEmpty()) {
                UserToken token = new UserToken();
                token.setUser(optionalUser.get());
                token.setRefreshToken(tokenProvider.generateToken(optionalUser.get(), JWTTokenProvider.TokenType.REFRESH));
                return tokenRepository.save(token);
            } else {
                return userToken.get();
            }
        }
        throw new UserNotFoundException();
    }

    public void deleteByUserId(Integer userId, String fingerprint) {
        tokenRepository.deleteByUserIdAndFingerprint(userId, fingerprint);
    }
}
