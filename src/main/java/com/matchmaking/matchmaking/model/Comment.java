package com.matchmaking.matchmaking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String author;
}
