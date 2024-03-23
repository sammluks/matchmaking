package com.matchmaking.matchmaking.dto;

import com.matchmaking.matchmaking.model.Comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private Long authorId;
    private Long postId;
    private String body;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.authorId = comment.getAuthor().getId();
        this.postId = comment.getPost().getId();
        this.body = comment.getBody();
    }
}
