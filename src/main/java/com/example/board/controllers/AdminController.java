package com.example.board.controllers;

import com.example.board.models.enums.Role;
import com.example.board.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin/panel";
    }

    @PostMapping("/user/ban/{id}")
    public String userBan(@PathVariable Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user/edit/{id}")
    public String assignRole(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", Role.values());
        return "admin/role-edit";
    }

    @PostMapping("/user/edit")
    public String assignRole(@RequestParam Long id,
                             @RequestParam String role) {
        userService.changeUserRole(userService.getUserById(id), role);
        return "redirect:/admin";
    }
}
