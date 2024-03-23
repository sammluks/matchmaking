package com.matchmaking.matchmaking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matchmaking.matchmaking.dto.PostDto;
import com.matchmaking.matchmaking.model.Post;
import com.matchmaking.matchmaking.model.User;
import com.matchmaking.matchmaking.repository.PostRepository;
import com.matchmaking.matchmaking.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PostService {
    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void delete(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException());
        repository.delete(post);
    }

    @Transactional
    public void register(PostDto postDto) {
        User author = userRepository.findById(postDto.getAuthor())
                .orElseThrow(() -> new EntityNotFoundException());

        Post post = new Post(postDto.getTitle(), postDto.getBody(), author);
        repository.save(post);
    }

    @Transactional
    public Page<PostDto> listPosts(Pageable pageable) {
        return repository.findAll(pageable).map(PostDto::new);
    }

    @Transactional
    public PostDto getPost(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException());
        return new PostDto(post);
    }
}
