package com.matchmaking.matchmaking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matchmaking.matchmaking.dto.AuthenticationData;
import com.matchmaking.matchmaking.dto.RegisterUserDto;
import com.matchmaking.matchmaking.infra.security.TokenService;
import com.matchmaking.matchmaking.infra.security.dto.TokenData;
import com.matchmaking.matchmaking.model.User;
import com.matchmaking.matchmaking.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody @Valid AuthenticationData data) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(data.getUsername(),
                data.getPassword());
        Authentication auth = manager.authenticate(authToken);

        String jwtToken = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenData(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDto data) {
        if (this.userRepository.findByUsername(data.getUsername()) != null)
            return ResponseEntity.badRequest().build();

        String encriptedPassword = passwordEncoder.encode(data.getPassword());

        User user = new User(data.getName(), data.getUsername(), data.getEmail(), encriptedPassword);

        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
