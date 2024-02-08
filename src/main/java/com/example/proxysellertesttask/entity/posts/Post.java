package com.example.proxysellertesttask.entity.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {

    @Transient
    public static final String SEQUENCE_NAME = "posts_sequence";

    @Id
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime editedAt;

    private Long userId;

    private List<Long> likes;

}
