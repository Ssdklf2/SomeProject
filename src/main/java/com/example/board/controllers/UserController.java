package com.example.board.controllers;

import com.example.board.models.DTO.UserDto;
import com.example.board.models.User;
import com.example.board.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("user/registration")
    public String registration(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "security/registration";
    }

    @PostMapping("user/registration")
    public String createUser(@Valid UserDto user) {
        userService.createUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("user/login")
    public String login() {
        return "security/login";
    }


    @GetMapping("user/{user}")
    public String userInfo(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("messages", user.getMessages());
        return "user/user-info";
    }

    @GetMapping("/account")
    public String getInfoToUser(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        String time = user.getDateOfCreated().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        model.addAttribute("user", user);
        model.addAttribute("time", time);
        model.addAttribute("messages", user.getMessages());
        return "/user/account";
    }

    @PostMapping("/account/change")
    public String editUserData(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber,
            Principal principal
    ) {
        userService.changeUser(principal, name, phoneNumber);
        return "redirect:/account";
    }
}
