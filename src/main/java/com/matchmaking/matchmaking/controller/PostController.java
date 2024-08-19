package com.matchmaking.matchmaking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.matchmaking.matchmaking.dto.CommentDto;
import com.matchmaking.matchmaking.dto.PostDto;
import com.matchmaking.matchmaking.dto.PostListDto;
import com.matchmaking.matchmaking.dto.RegisterPostDto;
import com.matchmaking.matchmaking.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<Page<PostListDto>> returnAllPosts(
            @PageableDefault(size = 10, page = 0, sort = { "id" }, direction = Direction.DESC) Pageable pageable) {
        Page<PostListDto> list = service.listPosts(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> returnPost(@PathVariable Long id) {
        PostDto post = service.getPost(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPost(@PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("body") String body) {
                PostDto postDto = new PostDto();
                postDto.setId(id);
                postDto.setTitle(title);
                postDto.setBody(body);
                service.editPost(postDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addComment")
    public ResponseEntity<CommentDto> addComment(@RequestBody @Valid CommentDto commentDto,
            UriComponentsBuilder uriBuilder) {
        service.addComment(commentDto);
        var uri = uriBuilder.path("/posts/{id}").buildAndExpand(commentDto.getId()).toUri();
        return ResponseEntity.created(uri).body(commentDto);
    }

    @PostMapping("/auth")
    public String registerPost(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "body") String body,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "author", required = false) String author,
            UriComponentsBuilder uriBuilder) {
        RegisterPostDto registerPostDto = new RegisterPostDto(image, title, body, category, author);
        service.register(registerPostDto);
        return null;
    }
}
