package com.example.proxysellertesttask.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscribes")
public class Subscribe {

    @Id
    private String id;

    private Long subscriberId;

    private Long targetUserId;

    public Subscribe(Long subscriber, Long targetUser) {
        this.subscriberId = subscriber;
        this.targetUserId = targetUser;
    }

}