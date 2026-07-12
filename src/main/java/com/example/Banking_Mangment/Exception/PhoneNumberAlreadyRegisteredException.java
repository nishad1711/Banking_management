package com.example.Banking_Mangment.Exception;

public class PhoneNumberAlreadyRegisteredException extends RuntimeException {

    public PhoneNumberAlreadyRegisteredException(String message) {
        super(message);
    }
}