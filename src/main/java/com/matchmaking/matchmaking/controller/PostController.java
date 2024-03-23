package com.matchmaking.matchmaking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matchmaking.matchmaking.dto.PostDto;

@RestController
@RequestMapping("/posts")
public class PostController {
    @PostMapping
    public String registerPost(@RequestBody PostDto postDto) {
        return postDto.toString();
    }

    @GetMapping
    public String returnAllPosts() {
        return new String();
    }

    @GetMapping("/{id}")
    public String returnPost(@PathVariable Long id) {
        return new String();
    }
    
}
