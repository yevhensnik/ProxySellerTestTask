package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.dto.CommentDto;
import com.example.proxysellertesttask.entity.posts.Comment;

public class CommentMapper {

    public static CommentDto mapToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .userId(comment.getUserId())
                .postId(comment.getPostId())
                .build();
    }
}
