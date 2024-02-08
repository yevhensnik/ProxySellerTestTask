package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.entity.posts.Favorite;
import com.example.proxysellertesttask.repository.FavoriteRepository;
import com.example.proxysellertesttask.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private PostRepository postRepository;

    public void markAsFavorite(Long userId, Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id " + postId + " not found"));

        if (!favoriteRepository.existsByUserIdAndPostId(userId, postId)) {
            Favorite favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setPostId(postId);
            favoriteRepository.save(favorite);
        }
    }

    public void leaveFavorite(Long userId, Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id " + postId + " not found"));

        favoriteRepository.deleteByUserIdAndPostId(userId, postId);
    }

    public boolean existsByUserIdAndPostId(Long userId, Long postId) {
        return favoriteRepository.existsByUserIdAndPostId(userId, postId);
    }
}
