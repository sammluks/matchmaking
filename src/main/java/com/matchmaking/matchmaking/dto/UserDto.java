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

    @NotBlank(message = "Campo userName não deve ser vazio")
    private String userName;

    @NotBlank(message = "Campo email não deve ser vazio")
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
