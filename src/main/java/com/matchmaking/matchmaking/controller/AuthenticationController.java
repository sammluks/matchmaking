package com.matchmaking.matchmaking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matchmaking.matchmaking.dto.AuthenticationData;
import com.matchmaking.matchmaking.infra.security.TokenService;
import com.matchmaking.matchmaking.infra.security.dto.TokenData;
import com.matchmaking.matchmaking.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody @Valid AuthenticationData data) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(data.getUsername(),
                data.getPassword());
        Authentication auth = manager.authenticate(authToken);

        String jwtToken = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenData(jwtToken));
    }
}
