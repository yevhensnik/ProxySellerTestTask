package com.example.proxysellertesttask.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @Size(max = 280, message = "Tweet must be less then 280 characters")
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime editedAt;

    private Long userId;

    private List<Long> likes;

    private List<CommentDto> comments;
}
