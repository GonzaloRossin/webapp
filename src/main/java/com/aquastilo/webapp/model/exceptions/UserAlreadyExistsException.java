package com.aquastilo.webapp.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("User already exists");
    }
}
