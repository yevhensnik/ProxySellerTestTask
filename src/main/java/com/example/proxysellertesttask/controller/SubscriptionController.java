package com.example.proxysellertesttask.controller;

import com.example.proxysellertesttask.service.SubscribeService;
import com.example.proxysellertesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SubscriptionController {

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private UserService userService;

    @PostMapping("/subscribe/to/{userId}")
    public ResponseEntity<String> subscribeToUser(@PathVariable Long userId) {

        subscribeService.subscribe(userService.getCurrentUser().getId(), userId);
        return ResponseEntity.ok("Subscribed successfully");
    }

    @PostMapping("/unsubscribe/from/{userId}")
    public ResponseEntity<String> unsubscribeFromUser(@PathVariable Long userId) {

        subscribeService.unsubscribe(userService.getCurrentUser().getId(), userId);
        return ResponseEntity.ok("Unsubscribed successfully");
    }
}
