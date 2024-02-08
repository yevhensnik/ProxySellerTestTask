package com.example.proxysellertesttask.repository;

import java.util.List;
import java.util.Optional;

import com.example.proxysellertesttask.entity.token.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<Token, Long> {

    List<Token> findAllByUserIdAndExpiredAndRevoked(Long userId, boolean expired, boolean revoked);

    Optional<Token> findByToken(String token);

    Optional<Token> findByTokenAndUserId(String token, Long userId);


}