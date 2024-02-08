package com.example.proxysellertesttask.repository;

import com.example.proxysellertesttask.entity.posts.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends MongoRepository<Like, Long> {

    boolean existsByUserIdAndPostId(Long user, Long post);

    Optional<Like> findByUserIdAndPostId(Long user, Long post);
}