package com.matchmaking.matchmaking.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matchmaking.matchmaking.dto.CommentDto;
import com.matchmaking.matchmaking.dto.PostDto;
import com.matchmaking.matchmaking.dto.PostListDto;
import com.matchmaking.matchmaking.dto.RegisterPostDto;
import com.matchmaking.matchmaking.model.Post;
import com.matchmaking.matchmaking.model.User;
import com.matchmaking.matchmaking.repository.PostRepository;
import com.matchmaking.matchmaking.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    @Transactional
    public void delete(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException());
        repository.delete(post);
    }

    @Transactional
    public void register(RegisterPostDto registerPostDto) {
        User author = userRepository.getByUsername(registerPostDto.getAuthor());

        Post post = new Post();
        post.setTitle(registerPostDto.getTitle());
        post.setBody(registerPostDto.getBody());
        try {
            post.setImage(registerPostDto.getImage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setAuthor(author);
        repository.save(post);
    }

    @Transactional
    public Page<PostListDto> listPosts(Pageable pageable) {
        return repository.findAll(pageable).map(PostListDto::new);
    }

    @Transactional
    public PostDto getPost(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException());
        return new PostDto(post);
    }

    @Transactional
    public void addComment(CommentDto commentDto) {
        commentService.addComment(commentDto);
    }

    @Transactional
    public void editPost(PostDto postDto) {
        repository.findById(postDto.getId()).ifPresentOrElse(post -> {
            post.setTitle(postDto.getTitle());
            post.setBody(postDto.getBody());
        }, () -> new EntityNotFoundException());
    }
}
