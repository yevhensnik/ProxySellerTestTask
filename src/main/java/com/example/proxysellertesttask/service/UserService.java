package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.dto.UserDto;
import com.example.proxysellertesttask.repository.UserRepository;
import com.example.proxysellertesttask.entity.user.User;
import com.example.proxysellertesttask.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<UserDto> editUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("id ERROR MESSAGE WHEN TRY TO FIND USER"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        //TODO add email validation
        user.setEmail(userDto.getEmail());

        //TODO add phone number validation
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setBio(userDto.getBio());

        userRepository.save(user);
        return ResponseEntity.ok(UserMapper.mapToDto(user));
    }

    public UserDto togglePrivateAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("id ERROR MESSAGE WHEN TRY TO FIND USER"));
        user.setPrivateAccount(!user.isPrivateAccount());

        return UserMapper.mapToDto(user);
    }

    public UserDto getUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("id ERROR MESSAGE WHEN TRY TO FIND USER"));
        return UserMapper.mapToDto(user);
    }


    public ResponseEntity<String> deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.delete(user.get());
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("Deleted successful ");
        } else {
            throw new ResourceNotFoundException("id ERROR MESSAGE WHEN TRY TO FIND USER");
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

//    private void validateCreateUserRequest(@Valid UserDto createUserRequest) {
//        if (createUserRequest.getUsername() == null || createUserRequest.getUsername().isBlank()) {
//            throw new IllegalArgumentException("Username must not be blank");
//        }
//
//        // You can add more validation logic based on your requirements
//    }


}
