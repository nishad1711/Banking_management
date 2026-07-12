package com.example.Banking_Mangment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BlacklistedToken {

    @Id
    private String token;

    private Date expiryDate;
}