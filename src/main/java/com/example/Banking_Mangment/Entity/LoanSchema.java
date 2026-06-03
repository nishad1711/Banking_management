package com.example.Banking_Mangment.Entity;

import com.example.Banking_Mangment.Entity.Type.LoanProviderType;
import com.example.Banking_Mangment.Entity.Type.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LoanSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Enumerated(EnumType.STRING)
    private LoanProviderType providerType;

    private Double maxAmount;

    private Double interestRate;

    private Integer tenureMonths;

    private boolean active;
}
