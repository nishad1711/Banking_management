package com.example.Banking_Mangment.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txt_id;
    private long transaction_id;
    private double balance;
    private LocalTime created_time;
    private LocalDate created_date;
    private boolean status;

}
