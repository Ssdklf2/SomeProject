package com.example.board.models.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageSorter {
    private String type;
    private String city;
    private Integer priceFrom;
    private Integer priceTo;
    private String title;
}
