package com.example.Banking_Mangment.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;

    private String password;
}