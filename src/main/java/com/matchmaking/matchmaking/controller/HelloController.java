package com.matchmaking.matchmaking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${TEXTO}")
    private String texto;

    @GetMapping
    public String getMethodName() {
        return texto;
    }
}
