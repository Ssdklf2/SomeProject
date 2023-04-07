package com.example.board.controllers;

import com.example.board.exceptions.NotFoundException;
import com.example.board.models.Message;
import com.example.board.services.MessageService;
import com.example.board.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping
    public String getAllMessages(@RequestParam(required = false) String title,
                                 Model model,
                                 Principal principal) {
        model.addAttribute("messages", messageService.getAllMessage(title));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "message/messages";
    }

    @GetMapping("/{id}")
    public String getMessageInfo(@PathVariable Long id, Model model) {
        Message message = messageService.getMessageById(id);
        if (message == null) throw new NotFoundException("Объявления не найдено");
        model.addAttribute("message", message);
        model.addAttribute("images", message.getImages());
        return "message/message-info";
    }

    @PostMapping("/create")
    public String createMessage(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2,
            @RequestParam("file3") MultipartFile file3,
            Message message,
            Principal principal) {
        List<MultipartFile> list = new ArrayList<>();
        addAllFilesToList(file1, file2, file3, list);
        messageService.saveMessage(principal, message, list);
        return "redirect:/message";
    }


    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return "redirect:/user/account";
    }

    @GetMapping("/sort")
    public String sort(Model model,
                       @RequestParam(required = false, defaultValue = "ALL") String type,
                       @RequestParam(required = false) String title,
                       @RequestParam(required = false) String city,
                       @RequestParam(required = false) Integer priceFrom,
                       @RequestParam(required = false) Integer priceTo
    ) {
        List<Message> messages = messageService.sort(type, city, priceFrom, priceTo, title);
        model.addAttribute("messages", messages);
        return "message/message-sort";
    }

    private void addAllFilesToList(MultipartFile file1, MultipartFile file2, MultipartFile file3,
                                   List<MultipartFile> list) {
        if (file1 != null) list.add(file1);
        if (file2 != null) list.add(file2);
        if (file3 != null) list.add(file3);
    }
}
