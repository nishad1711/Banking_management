package com.example.Banking_Mangment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Data
public class Account {
    @Id
    private Long account_id;
    private Long user_id;
    private double balance;
    private LocalTime created_at;

}
