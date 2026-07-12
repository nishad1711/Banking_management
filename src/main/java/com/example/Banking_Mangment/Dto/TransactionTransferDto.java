package com.example.Banking_Mangment.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionTransferDto {
    @Pattern(regexp = "^\\d{10}$", message = "Value must be exactly 10 digits")
    private String receiverPhone;
    private double amount;
}