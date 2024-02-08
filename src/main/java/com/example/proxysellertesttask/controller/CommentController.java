package com.example.proxysellertesttask.controller;

import com.example.proxysellertesttask.dto.CommentDto;
import com.example.proxysellertesttask.dto.CommentRequest;
import com.example.proxysellertesttask.service.CommentService;
import com.example.proxysellertesttask.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.createComment(
                commentRequest.getContent(),
                userService.getCurrentUser().getId(),
                commentRequest.getPostId()));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
}