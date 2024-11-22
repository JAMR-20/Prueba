package com.example.pruebaTecnica.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
