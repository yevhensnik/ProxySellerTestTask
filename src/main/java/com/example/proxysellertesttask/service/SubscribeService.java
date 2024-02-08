package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.entity.user.Subscribe;
import com.example.proxysellertesttask.entity.user.User;
import com.example.proxysellertesttask.repository.SubscribeRepository;
import com.example.proxysellertesttask.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private UserRepository userRepository;

    public void subscribe(Long subscriberId, Long targetUserId) {
        User subscriber = getUserById(subscriberId);
        User targetUser = getUserById(targetUserId);

        if (!isAlreadySubscribed(subscriber, targetUser)) {
            Subscribe subscribe = new Subscribe(subscriber.getId(), targetUser.getId());
            subscribeRepository.save(subscribe);

            subscriber.incrementFollowingCount();
            targetUser.incrementFollowersCount();
            userRepository.save(subscriber);
            userRepository.save(targetUser);
        }
    }

    public void unsubscribe(Long subscriberId, Long targetUserId) {
        User subscriber = getUserById(subscriberId);
        User targetUser = getUserById(targetUserId);

        if (isAlreadySubscribed(subscriber, targetUser)) {
            subscribeRepository.deleteBySubscriberIdAndTargetUserId(subscriber.getId(), targetUser.getId());

            subscriber.decrementFollowingCount();
            targetUser.decrementFollowersCount();
            userRepository.save(subscriber);
            userRepository.save(targetUser);
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    }

    private boolean isAlreadySubscribed(User subscriber, User targetUser) {
        return subscribeRepository.existsBySubscriberIdAndTargetUserId(subscriber.getId(), targetUser.getId());
    }

    public List<Long> getSubscribedUserIds(User user) {
        List<Subscribe> subscriptions = subscribeRepository.findBySubscriberId(user.getId());
        return subscriptions.stream()
                .map(Subscribe::getTargetUserId)
                .collect(Collectors.toList());
    }
}
