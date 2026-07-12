package com.example.Banking_Mangment.Exception;

public class LoanSchemeNotFoundException extends RuntimeException {

    public LoanSchemeNotFoundException(String message) {
        super(message);
    }
}