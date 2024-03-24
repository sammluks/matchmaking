package com.matchmaking.matchmaking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationData {
    private String username;
    private String password;
}
