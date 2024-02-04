package com.aquastilo.webapp.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User does not exist");
    }
}
