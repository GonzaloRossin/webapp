package com.aquastilo.webapp.model.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product does not exist");
    }
}
