package com.example.proxysellertesttask.entity.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    @Field("_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String bio;
    private LocalDate joinDate;
    private LocalDate lastVisitDate;
    private int followersCount;
    private int followingCount;
    private int tweetCount;
    private int likesCount;
    private boolean privateAccount;

    @Enumerated(EnumType.STRING)
    private Role role;

    private List<Long> subscriptions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void incrementFollowingCount() {
        this.followingCount++;
    }

    public void decrementFollowingCount() {
        if (this.followingCount > 0) {
            this.followingCount--;
        }
    }

    public void incrementFollowersCount() {
        this.followersCount++;
    }

    public void decrementFollowersCount() {
        if (this.followersCount > 0) {
            this.followersCount--;
        }
    }
}
