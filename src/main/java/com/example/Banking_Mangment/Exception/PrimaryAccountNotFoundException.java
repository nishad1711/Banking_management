package com.example.Banking_Mangment.Exception;

public class PrimaryAccountNotFoundException extends RuntimeException {

    public PrimaryAccountNotFoundException(String message) {
        super(message);
    }
}