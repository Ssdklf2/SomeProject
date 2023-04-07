package com.example.board.models.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Field may not be empty")
//    @Size(min = 2, max = 32, message = "Field must be between 2 and 32 characters long")
    private String email;

    @NotBlank(message = "Field may not be empty")
//    @Size(min = 2, max = 32, message = "Field must be between 2 and 32 characters long")
    private String phoneNumber;

    @NotBlank(message = "Field may not be empty")
//    @Size(min = 2, max = 32, message = "Field must be between 2 and 32 characters long")
    private String name;

    @NotBlank(message = "Field may not be empty")
//    @Size(min = 2, max = 32, message = "Field must be between 2 and 32 characters long")
    private String password;

    @NotBlank(message = "Field may not be empty")
//    @Size(min = 2, max = 32, message = "Field must be between 2 and 32 characters long")
    private String passwordRepeat;
}
