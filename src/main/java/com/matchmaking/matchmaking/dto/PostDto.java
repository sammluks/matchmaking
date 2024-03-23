package com.matchmaking.matchmaking.dto;

import java.util.ArrayList;
import java.util.List;

import com.matchmaking.matchmaking.model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private Long author;
    private List<CommentDto> comments = new ArrayList<>();

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.author = post.getAuthor().getId();
    }
}
