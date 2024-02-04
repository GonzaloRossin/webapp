package com.aquastilo.webapp.model.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(){
        super("Category does not exist");
    }
}
