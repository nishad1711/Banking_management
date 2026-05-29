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

    private long sender_id;
    private long receiver_id;
    private double amount;
    private LocalTime sending_time;
    private LocalDate sending_date;
    private boolean status;

}
