package com.matchmaking.matchmaking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.matchmaking.matchmaking.dto.CommentDto;
import com.matchmaking.matchmaking.dto.PostDto;
import com.matchmaking.matchmaking.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<?> registerPost(@RequestBody @Valid PostDto postDto, UriComponentsBuilder uriBuilder) {
        service.register(postDto);
        var uri = uriBuilder.path("/posts/{id}").buildAndExpand(postDto.getId()).toUri();
        return ResponseEntity.created(uri).body(postDto);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> returnAllPosts(
            @PageableDefault(size = 10, page = 0, sort = { "title" }) Pageable pageable) {
        Page<PostDto> list = service.listPosts(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> returnUser(@PathVariable Long id) {
        PostDto post = service.getPost(id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addComment")
    public ResponseEntity<CommentDto> addComment(@RequestBody @Valid CommentDto commentDto, UriComponentsBuilder uriBuilder) {
        service.addComment(commentDto);
        var uri = uriBuilder.path("/posts/{id}").buildAndExpand(commentDto.getId()).toUri();
        return ResponseEntity.created(uri).body(commentDto);
    }
}
