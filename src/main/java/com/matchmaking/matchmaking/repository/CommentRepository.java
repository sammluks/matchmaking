package com.matchmaking.matchmaking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matchmaking.matchmaking.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
