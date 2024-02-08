package com.example.proxysellertesttask.controller;

import com.example.proxysellertesttask.service.FavoriteService;
import com.example.proxysellertesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> markAsFavorite(@PathVariable Long postId) {
        favoriteService.markAsFavorite(userService.getCurrentUser().getId(), postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> leaveFavorite(@PathVariable Long postId) {
        if (!favoriteService.existsByUserIdAndPostId(userService.getCurrentUser().getId(), postId)) {
            return ResponseEntity.notFound().build();
        }

        favoriteService.leaveFavorite(userService.getCurrentUser().getId(), postId);
        return ResponseEntity.ok().build();
    }
}