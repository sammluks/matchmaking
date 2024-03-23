package com.matchmaking.matchmaking.dto;

import com.matchmaking.matchmaking.model.User;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
