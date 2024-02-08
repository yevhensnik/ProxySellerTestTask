package com.example.proxysellertesttask.controller;

import com.example.proxysellertesttask.dto.CommentDto;
import com.example.proxysellertesttask.dto.PostDto;
import com.example.proxysellertesttask.entity.user.User;
import com.example.proxysellertesttask.service.CommentService;
import com.example.proxysellertesttask.service.LikeService;
import com.example.proxysellertesttask.service.PostService;
import com.example.proxysellertesttask.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")

public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createPost(postDto, userService.getCurrentUser().getId()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.editPost(postId, postDto, userService.getCurrentUser().getId()));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.deletePost(postId, userService.getCurrentUser().getId()));
    }

    @GetMapping("/subscribed-feed")
    public ResponseEntity<List<PostDto>> getUserFeed() {
        return ResponseEntity.ok(postService.getSubscribedUserFeed(userService.getCurrentUser()));
    }

    @GetMapping("/another-user-feed")
    public ResponseEntity<List<PostDto>> getAnotherUserFeed() {
        return ResponseEntity.ok(postService.getAnotherUserFeed());
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        likeService.likePost(userService.getCurrentUser().getId(), postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/unlike")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        likeService.unlikePost(userService.getCurrentUser().getId(), postId);
        return ResponseEntity.ok().build();
    }
}
