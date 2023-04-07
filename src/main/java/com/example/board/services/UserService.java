package com.example.board.services;

import com.example.board.exceptions.NotFoundException;
import com.example.board.exceptions.PasswordsNotEquals;
import com.example.board.exceptions.UserWithEmailAlreadyExist;
import com.example.board.models.DTO.UserDto;
import com.example.board.models.User;
import com.example.board.models.enums.Role;
import com.example.board.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        String userEmail = userDto.getEmail();
        if (userRepo.findUserByEmail(userEmail) != null) {
            throw new UserWithEmailAlreadyExist(
                    "User with email " + userEmail + " already exist");
        }
        if (!userDto.getPassword().equals(userDto.getPasswordRepeat())) {
            throw new PasswordsNotEquals("Пароль не совпадает");
        }
        User user = getUserFromDto(userDto);
        userRepo.save(user);
        log.info("Saving new User with userEmail: " + userEmail + " phone: " + userDto.getPhoneNumber());
    }

    private User getUserFromDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        return user;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findUserByEmail(principal.getName());
    }

    public List<User> list() {
        return userRepo.findAll();
    }

    public void banUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("User with Id: " + id + " is banned");
            } else {
                user.setActive(true);
                log.info("User with Id: " + id + " is unbanned");
            }
            userRepo.save(user);
        }
    }

    public void changeUserRole(User user, String role) {
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf(role));
        userRepo.save(user);
    }

    public void changeUser(Principal principal, String name, String phoneNumber) {
        User user = getUserByPrincipal(principal);
        if (name != null && !name.isEmpty()) user.setName(name);
        if (phoneNumber != null && !phoneNumber.isEmpty()) user.setPhoneNumber(phoneNumber);
        userRepo.save(user);
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Пользователь c id " + id + " не найден"));
    }
}
