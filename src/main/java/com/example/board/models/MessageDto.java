package com.example.board.models;

import com.example.board.models.enums.MessageType;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Message} entity
 */
@Data
public class MessageDto implements Serializable {
    private final String title;
    private final String description;
    private final MessageType messageType;
    private final int price;
    private final String city;
}