package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.dto.CommentDto;
import com.example.proxysellertesttask.dto.PostDto;
import com.example.proxysellertesttask.entity.posts.Post;

import java.util.List;

public class PostMapper {

    public static PostDto mapToDto(Post post, List<Long> likes, List<CommentDto> comments) {
        return PostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .editedAt(post.getEditedAt())
                .userId(post.getUserId())
                .likes(likes)
                .comments(comments)
                .build();
    }
}
