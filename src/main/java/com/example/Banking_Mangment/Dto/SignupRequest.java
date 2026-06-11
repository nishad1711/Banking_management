package com.example.Banking_Mangment.Dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    private String pin;
}