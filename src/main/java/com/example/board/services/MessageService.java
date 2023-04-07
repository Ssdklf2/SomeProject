package com.example.board.services;

import com.example.board.models.Image;
import com.example.board.models.Message;
import com.example.board.repositories.MessageRepo;
import com.example.board.specifications.MessageSpecification;
import com.example.board.specifications.SearchCriteria;
import com.example.board.specifications.SearchOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;
    private final UserService userService;

    public List<Message> getAllMessage(String title) {
        if (title != null) return messageRepo.findByTitle(title);
        return messageRepo.findAll();
    }

    @Transactional
    public void saveMessage(Principal principal,
                            Message message,
                            List<MultipartFile> files
    ) {
        message.setUser(userService.getUserByPrincipal(principal));
        log.info("Saving new Message. Title: {}, Author email: {}", message.getTitle(), message.getUser().getEmail());
        if (!files.get(0).isEmpty()) {
            Image image = toImageFromArray(files, message);
            messageRepo.save(message);
            message.setPreviewImageId(message.getImages().get(0).getId());
        }
        messageRepo.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }

    public List<Message> sort(String messageType, String city, Integer priceFrom,
                              Integer priceTo, String title) {
        log.info("SEARCHING " +
                "\n TYPE:  " + messageType +
                "\n CITY:  " + city +
                "\n PRICE:  " + priceFrom + " - " + priceTo +
                "\n TITLE:  " + title);
        MessageSpecification specification = new MessageSpecification();
        if (!messageType.equals("ALL")) {
            specification.addCriteria(
                    new SearchCriteria("messageType", messageType, SearchOperation.EQUAL));
        }
        if (city != null && !city.isBlank()) {
            specification.addCriteria(
                    new SearchCriteria("city", city, SearchOperation.EQUAL));
        }
        if (title != null && !title.isBlank()) {
            specification.addCriteria(
                    new SearchCriteria("title", title, SearchOperation.LIKE));
        }
        if (priceFrom != null && priceTo != null) {
            specification.addCriteria(
                    new SearchCriteria("price",
                            String.valueOf(priceFrom),
                            String.valueOf(priceTo),
                            SearchOperation.BETWEEN));
        }
        if (priceFrom != null && priceTo == null) {
            specification.addCriteria(
                    new SearchCriteria("price",
                            String.valueOf(priceFrom),
                            SearchOperation.GREATER_THAN));
        }
        if (priceFrom == null && priceTo != null) {
            specification.addCriteria(
                    new SearchCriteria("price",
                            String.valueOf(priceTo),
                            SearchOperation.LESS_THAN));
        }
        return messageRepo.findAll(specification);
    }

    public Message getMessageById(Long id) {
        return messageRepo.findById(id).orElse(null);
    }

    private Image toImageFromArray(List<MultipartFile> files, Message message) {
        Image image;
        Image preview = null;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getSize() != 0) {
                image = toImageEntity(files.get(i));
                if (i == 0) {
                    image.setPreviewImage(true);
                    preview = image;
                }
                message.addImageToMessage(image);
            }
        }
        return preview;
    }

    private Image toImageEntity(MultipartFile file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        log.info("ID " + image.getId());
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

}
