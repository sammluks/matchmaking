package com.matchmaking.matchmaking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matchmaking.matchmaking.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
