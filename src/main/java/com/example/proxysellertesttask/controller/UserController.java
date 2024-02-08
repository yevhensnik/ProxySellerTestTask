package com.example.proxysellertesttask.controller;

import com.example.proxysellertesttask.dto.UserDto;
import com.example.proxysellertesttask.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/edit")
    public ResponseEntity<UserDto> editUser(@Valid @RequestBody UserDto userDto) {
        return userService.editUser(userService.getCurrentUser().getId(), userDto);
    }

    @PostMapping("/toggle-private-account")
    public ResponseEntity<UserDto> togglePrivateAccount() {
        return ResponseEntity.ok(userService.togglePrivateAccount(userService.getCurrentUser().getId()));
    }

    @GetMapping("/user-data")
    public ResponseEntity<UserDto> getUserData() {
        return ResponseEntity.ok(userService.getUser(userService.getCurrentUser().getId()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        return userService.deleteUser(userService.getCurrentUser().getId());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
