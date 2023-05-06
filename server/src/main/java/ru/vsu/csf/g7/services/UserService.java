package ru.vsu.csf.g7.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.exception.ApiException;
import ru.vsu.csf.g7.exception.LoginAlreadyInUseException;
import ru.vsu.csf.g7.exception.UserNotFoundException;
import ru.vsu.csf.g7.payload.request.SignupRequest;
import ru.vsu.csf.g7.payload.request.UpdateUserRequest;
import ru.vsu.csf.g7.repos.RoleRepository;
import ru.vsu.csf.g7.repos.UserRepository;

import javax.validation.Valid;
import java.security.Permission;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository usersRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

//    public User createUser(SignupRequest newUser) {
//        User user = new User();
//        user.setEmail(newUser.getEmail());
//        user.setLogin(newUser.getLogin());
//        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
//
//        Permission p = BasePermission.ADMINISTRATION;
//
//        if (newUser.getSecretKey().equals(secretKey) && newUser.getRole() != null) {
//            Role role = roleRepository.findByRole(newUser.getRole())
//                    .orElse(roleRepository.findById(2L).get());
//            user.setRole(role);
//        } else
//            user.setRole(roleRepository.findByRole(ERole.ROLE_USER).get());
//
//        Optional<User> res;
//        String msg = "";
//        if ((res = userRepository.findByLoginOrEmail(newUser.getLogin(), newUser.getEmail())).isPresent()) {
//            User u = res.get();
//            if (u.getEmail().equals(newUser.getEmail()))
//                msg += "Такой email уже занят\n";
//            if (u.getLogin().equals(newUser.getLogin()))
//                msg += "Логин уже занят\n";
//            throw new ApiException(msg);
//        }
//
//        try {
//            log.info("Сохранение пользователя в базу {}", newUser.getLogin());
//            return userRepository.save(user);
//        } catch (Exception e) {
//            throw new ApiException("Неизвестная ошибка при регистрации");
//        }
//    }

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
        userRepository.deleteById(id);
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
