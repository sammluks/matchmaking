package com.matchmaking.matchmaking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDto {
    
    @NotBlank(message = "Campo username não deve ser vazio")
    private String username;
    
    @NotBlank(message = "Campo name não deve ser vazio")
    private String name;

    @NotBlank(message = "Campo email não deve ser vazio")
    private String email;

    @NotBlank(message = "Campo senha não deve ser vazio")
    private String password;
}
