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

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody @Valid AuthenticationData data) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        Authentication auth = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
