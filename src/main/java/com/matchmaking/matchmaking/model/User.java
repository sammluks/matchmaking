package com.matchmaking.matchmaking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// @Entity(name = "User")
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String name;
    private String userName;
    private String email;
}
