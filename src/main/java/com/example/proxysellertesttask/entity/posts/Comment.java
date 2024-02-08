package com.example.proxysellertesttask.entity.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Transient
    public static final String SEQUENCE_NAME = "comments_sequence";

    @Id
    private Long id;

    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private Long postId;

}