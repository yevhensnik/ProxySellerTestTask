package com.example.proxysellertesttask.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private Long postId;
}
