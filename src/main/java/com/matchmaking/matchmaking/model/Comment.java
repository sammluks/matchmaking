package com.matchmaking.matchmaking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "post", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;
}
