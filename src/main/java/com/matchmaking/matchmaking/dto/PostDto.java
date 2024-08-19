package com.matchmaking.matchmaking.dto;

import com.matchmaking.matchmaking.model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private String author;
    private byte[] image;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.author = post.getAuthor().getName();
        this.image = post.getImage();
    }
}
