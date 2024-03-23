package com.matchmaking.matchmaking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matchmaking.matchmaking.dto.CommentDto;
import com.matchmaking.matchmaking.model.Comment;
import com.matchmaking.matchmaking.model.Post;
import com.matchmaking.matchmaking.model.User;
import com.matchmaking.matchmaking.repository.CommentRepository;
import com.matchmaking.matchmaking.repository.PostRepository;
import com.matchmaking.matchmaking.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new EntityNotFoundException());

        User author = userRepository.findById(commentDto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException());

        Comment parentComment = null;
        if (commentDto.getParentCommentId() != null) {
            parentComment = repository.findById(commentDto.getParentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException());
        }
        
        Comment comment = new Comment(commentDto, parentComment, post, author);
        repository.saveAndFlush(comment);
    }
}
