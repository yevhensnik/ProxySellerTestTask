package com.example.proxysellertesttask.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String msg) {
        super(msg);
    }
}
