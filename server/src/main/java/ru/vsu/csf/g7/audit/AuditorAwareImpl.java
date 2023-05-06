package ru.vsu.csf.g7.audit;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.repos.UserRepository;
import ru.vsu.csf.g7.services.UserService;

import java.security.Principal;
import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<User> {
    @Autowired
    private UserService userService;

    @Override
    public Optional<User> getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return userService.getUserByLogin("service");
        } else {
            val user = (User) authentication.getPrincipal();
            return Optional.ofNullable(userService.getUserById(user.getId()));
        }
    }
}
