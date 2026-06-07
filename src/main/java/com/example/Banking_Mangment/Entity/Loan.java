package com.example.Banking_Mangment.Entity;

import com.example.Banking_Mangment.Entity.Type.LoanProviderType;
import com.example.Banking_Mangment.Entity.Type.LoanStatus;
import com.example.Banking_Mangment.Entity.Type.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "loan_schema_id")
    private LoanSchema loanSchema;
}