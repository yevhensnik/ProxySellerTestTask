package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.entity.posts.Like;
import com.example.proxysellertesttask.entity.posts.Post;
import com.example.proxysellertesttask.entity.user.User;
import com.example.proxysellertesttask.exception.LikeNotFoundException;
import com.example.proxysellertesttask.repository.LikeRepository;
import com.example.proxysellertesttask.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    public void likePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id " + postId + " not found"));

        if (!hasLikedPost(userId, postId)) {
            Like like = new Like(sequenceGenerator.generateSequence(Like.SEQUENCE_NAME), userId, postId);
            likeRepository.save(like);
            List<Long> likes = post.getLikes();
            if (likes == null) {
                likes = new ArrayList<>();
            }
            likes.add(userId);
            post.setLikes(likes);
            Post savedPost = postRepository.save(post);
            System.out.println(savedPost.getLikes().size());
        }
    }

    public void unlikePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id " + postId + " not found"));

        if (hasLikedPost(userId, postId)) {
            Like like = likeRepository.findByUserIdAndPostId(userId, postId)
                    .orElseThrow(() -> new LikeNotFoundException("Like not found"));

            likeRepository.delete(like);

            List<Long> likes = post.getLikes();
            if (likes != null) {
                likes.remove(userId);
            }
            post.setLikes(likes);
            Post savedPost = postRepository.save(post);
            System.out.println(savedPost.getLikes().size());
        }
    }

    public boolean hasLikedPost(Long userId, Long postId) {
        return likeRepository.existsByUserIdAndPostId(userId, postId);
    }
}
