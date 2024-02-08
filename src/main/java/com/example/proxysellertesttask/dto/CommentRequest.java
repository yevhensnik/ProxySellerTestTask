package com.example.proxysellertesttask.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequest {

    @Size(max = 280, message = "Comment must be less then 280 characters")
    private String content;
    private Long postId;
}
