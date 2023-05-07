package ru.vsu.csf.g7.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.exception.ApiException;
import ru.vsu.csf.g7.exception.LoginAlreadyInUseException;
import ru.vsu.csf.g7.exception.UserAlreadyExistsException;
import ru.vsu.csf.g7.exception.UserNotFoundException;
import ru.vsu.csf.g7.payload.request.SignupRequest;
import ru.vsu.csf.g7.payload.request.UpdateUserRequest;
import ru.vsu.csf.g7.repos.UserRepository;

import javax.validation.Valid;
import java.security.Permission;
import java.security.Principal;
import java.util.*;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest newUser) {
        Optional<User> userByLoginOrEmail = userRepository.findUserByLoginOrEmail(newUser.getLogin(), newUser.getEmail());
        if (userByLoginOrEmail.isPresent()) {
            User u = userByLoginOrEmail.get();
            if (u.getEmail().equals(newUser.getEmail()))
                throw new UserAlreadyExistsException("c почтой", newUser.getEmail());
            if (u.getLogin().equals(newUser.getLogin()))
                throw new UserAlreadyExistsException(newUser.getEmail());
        }

        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setLogin(newUser.getLogin());
        user.setName(newUser.getName());
        user.setRole(newUser.getRole());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        try {
            log.debug("Сохранение пользователя в базу {}", newUser.getLogin());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ApiException("Неизвестная ошибка при регистрации");
        }
    }

    public User getCurrentUser(Principal principal) throws UserNotFoundException {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) throws UserNotFoundException {
        String login = principal.getName();
        return userRepository.getUserByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(login));

    }

    public User getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User updateCurrentUser(UpdateUserRequest req, Principal principal) throws ApiException {
        User user = getUserByPrincipal(principal);
        return update(req, user);
    }

    public User updateUserById(UpdateUserRequest req, Integer id) {
        User user = this.getUserById(id);
        return update(req, user);
    }

    private User update(@Valid UpdateUserRequest req, User user) {
        if (!user.getLogin().equalsIgnoreCase(req.getLogin()))
            user.setLogin(req.getLogin());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        if (userRepository.existsUserByLogin(req.getLogin()))
            throw new LoginAlreadyInUseException(user.getLogin());

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Ошибка при обновлении пользователя. {}", e.getMessage());
            throw new ApiException("Неизвестная ошибка при обновлении пользователя");
        }
    }

    public void removeUserById(Integer id) {
        userRepository.findById(id).ifPresentOrElse(user -> user.setAccountDeleted(true), () -> {
            throw new ApiException("Неизвестная ошибка при обновлении пользователя");
        });
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public List<User> getAllUsers() {
        return userRepository.getUsersBy();
    }
}
