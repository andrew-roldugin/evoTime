package ru.vsu.csf.g7.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.entity.UserToken;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findByRefreshToken(String rt);

    Optional<UserToken> findByUserIdAndFingerprint(Integer userId, String fingerprint);

    void deleteByUserIdAndFingerprint(Integer userId, String fingerprint);
}
