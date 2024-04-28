package com.matchmaking.matchmaking.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.matchmaking.matchmaking.model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private String title;
    // private String body;
    private String summary;
    private String author;
    private byte[] image;
    private List<CommentDto> comments = new ArrayList<>();

    public PostListDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor().getName();
        this.image = post.getImage();

        StringBuilder summary = new StringBuilder();
        String[] bodySplit = post.getBody().split(" ");
        Stream.of(bodySplit).limit(30).forEach(word -> summary.append(word + " "));
        this.summary = summary.deleteCharAt(summary.lastIndexOf(" ")).toString();
    }
}
