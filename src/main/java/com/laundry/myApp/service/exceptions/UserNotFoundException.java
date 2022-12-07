package com.laundry.myApp.service.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super("User with username: " + username + " cannot be founded!");
    }

}
