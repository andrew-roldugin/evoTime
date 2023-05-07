package ru.vsu.csf.g7.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.payload.request.UpdateUserRequest;
import ru.vsu.csf.g7.payload.response.MessageResponse;
import ru.vsu.csf.g7.payload.response.dto.UserDTO;
import ru.vsu.csf.g7.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        final List<UserDTO> allUsers = userService.getAllUsers().stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> loadProfile(@PathVariable("userId") String userId) {
        final Integer id = Integer.valueOf(userId);
        final User user = userService.getUserById(id);
        final UserDTO userDTO = UserDTO.fromUser(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/")
    public ResponseEntity<Object> myAccount(Principal principal) {
        final User user = userService.getCurrentUser(principal);
        UserDTO userDTO = UserDTO.fromUser(user);
        return ResponseEntity.ok(userDTO);
    }

    @PreAuthorize("#userId.equals(authentication.principal.id.toString()) or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest req, BindingResult bindingResult) {
        final User user = userService.updateUserById(req, Integer.valueOf(userId));
        return ResponseEntity.ok(UserDTO.fromUser(user));
    }

    @PreAuthorize("#userId.equals(authentication.principal.id.toString()) or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResponse> deleteUserAccount(@PathVariable("userId") String userId) {
        userService.removeUserById(Integer.valueOf(userId));
        return new ResponseEntity<>(new MessageResponse("Учетная запись успешно удалена"), HttpStatus.OK);
    }
}
