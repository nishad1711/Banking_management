package com.example.Banking_Mangment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonTransactionalHistoryDto {
    private double amount;
    private LocalTime sending_time;
    private LocalDate sending_date;
    private boolean status;
    private String moneyTransferTo;
    private Long fromAccount;

}
