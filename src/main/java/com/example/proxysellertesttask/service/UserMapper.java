package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.dto.UserDto;
import com.example.proxysellertesttask.entity.user.User;

public class UserMapper {
    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bio(user.getBio())
                .joinDate(user.getJoinDate())
                .lastVisitDate(user.getLastVisitDate())
                .followersCount(user.getFollowersCount())
                .followingCount(user.getFollowingCount())
                .tweetCount(user.getTweetCount())
                .likesCount(user.getLikesCount())
                .privateAccount(user.isPrivateAccount())
                .build();
    }
}
