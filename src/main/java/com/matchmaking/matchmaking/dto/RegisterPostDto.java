package com.matchmaking.matchmaking.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterPostDto {
    private MultipartFile image;
    private String title;
    private String body;
    private String category;
}
