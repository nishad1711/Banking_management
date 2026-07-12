package com.example.Banking_Mangment.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TransactiondetailsDto {
    private double amount;
    private LocalTime sending_time;
    private LocalDate sending_date;
    private boolean status;
}