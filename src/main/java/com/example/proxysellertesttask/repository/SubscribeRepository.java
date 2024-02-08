package com.example.proxysellertesttask.repository;

import com.example.proxysellertesttask.entity.user.Subscribe;
import com.example.proxysellertesttask.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends MongoRepository<Subscribe, String> {

    boolean existsBySubscriberIdAndTargetUserId(Long subscriber, Long targetUser);

    void deleteBySubscriberIdAndTargetUserId(Long subscriber, Long targetUser);

    Optional<Subscribe> findBySubscriberIdAndTargetUserId(Long subscriber, Long targetUser);

    List<Subscribe> findBySubscriberId(Long subscriber);

    List<Long> findTargetUserIdsBySubscriberId(Long subscriber);

}