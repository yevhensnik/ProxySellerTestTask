package com.example.proxysellertesttask.repository;

import com.example.proxysellertesttask.entity.posts.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, Long> {

    Optional<Post> findByIdAndUserId(Long postId, Long UserId);

    List<Post> findByUserIdInOrderByCreatedAtDesc(List<Long> userIds);
}
