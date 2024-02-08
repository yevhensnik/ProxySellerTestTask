package com.example.proxysellertesttask.entity.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "favorites")
public class Favorite {

    @Transient
    public static final String SEQUENCE_NAME = "favorites_sequence";

    @Id
    private String id;

    private Long userId;

    private Long postId;
}
