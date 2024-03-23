package com.matchmaking.matchmaking.model;

import java.util.ArrayList;
import java.util.List;

import com.matchmaking.matchmaking.dto.CommentDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "post", nullable = false)
    private Post post;
    
    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "parentComment", referencedColumnName = "id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments = new ArrayList<>();
    
    public Comment(CommentDto commentDto, Comment parentComment, Post post, User author) {
        this.body = commentDto.getBody();
        this.parentComment = parentComment;
        this.author = author;
        this.post = post;
    }
}
