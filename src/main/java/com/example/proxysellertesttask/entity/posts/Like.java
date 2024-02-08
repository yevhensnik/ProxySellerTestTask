package com.example.proxysellertesttask.entity.posts;

import com.example.proxysellertesttask.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "likes")
public class Like {

    @Transient
    public static final String SEQUENCE_NAME = "likes_sequence";

    @Id
    private Long id;

    private Long userId;
    private Long postId;
}