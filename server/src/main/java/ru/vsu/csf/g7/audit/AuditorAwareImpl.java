package ru.vsu.csf.g7.audit;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.repos.UserRepository;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return userRepository.getUserByLogin("service");
        } else {
            val user = (User) authentication.getPrincipal();
            return userRepository.findById(user.getId());
        }
    }
}
