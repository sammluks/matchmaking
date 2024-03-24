package com.matchmaking.matchmaking.dto;

import com.matchmaking.matchmaking.model.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    
    @NotBlank(message = "Campo name não deve ser vazio")
    private String name;

    @NotBlank(message = "Campo username não deve ser vazio")
    private String username;

    @NotBlank(message = "Campo email não deve ser vazio")
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
