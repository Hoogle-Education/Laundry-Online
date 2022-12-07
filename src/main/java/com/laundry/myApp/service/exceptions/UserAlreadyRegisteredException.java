package com.laundry.myApp.service.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String email) {
        super("User with email: " + email + " has already registered!");
    }
}
