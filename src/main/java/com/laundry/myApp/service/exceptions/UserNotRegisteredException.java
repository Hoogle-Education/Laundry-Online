package com.laundry.myApp.service.exceptions;

public class UserNotRegisteredException extends Throwable {
    public UserNotRegisteredException(String email) {
        super("User with email: " + email + " has not been registered.");

    }
}
