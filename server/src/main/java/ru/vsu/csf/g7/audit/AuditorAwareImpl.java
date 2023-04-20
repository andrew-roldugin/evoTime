package ru.vsu.csf.g7.audit;

import org.springframework.data.domain.AuditorAware;
import ru.vsu.csf.g7.entity.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.empty();
    }
}
