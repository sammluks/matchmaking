package com.matchmaking.matchmaking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matchmaking.matchmaking.dto.UserDto;
import com.matchmaking.matchmaking.model.User;
import com.matchmaking.matchmaking.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public void registerUser(@RequestBody @Valid UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getName(), userDto.getUserName(), userDto.getEmail());
        repository.save(user);
    }

    @GetMapping
    public Page<UserDto> returnAllUsers(@PageableDefault(size = 10, page = 0, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(UserDto::new);
    }

    @GetMapping("/{id}")
    public User returnUser(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

}
