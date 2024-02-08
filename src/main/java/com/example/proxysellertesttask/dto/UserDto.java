package com.example.proxysellertesttask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Email(message = "Invalid email address")
    private String email;

//    @NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password must be at least 6 characters")
//    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+?[1-9]|1[0-9])\\d{1,14}$", message = "Invalid phone number format.")
    private String phoneNumber;

    @Size(max = 250, message = "Bio must be between less then 250 characters")
    private String bio;

    private LocalDate joinDate;

    private LocalDate lastVisitDate;

    private int followersCount;

    private int followingCount;

    private int tweetCount;

    private int likesCount;

    private boolean privateAccount;

    private List<String> subscriptions; // User IDs of subscriptions

}
