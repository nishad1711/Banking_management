package com.example.Banking_Mangment.Entity;

import com.example.Banking_Mangment.Entity.Type.InsuranceStatus;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InsuranceStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "insurance_schema_id")
    private InsuranceSchema insuranceSchema;
}