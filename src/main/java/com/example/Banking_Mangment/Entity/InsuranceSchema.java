package com.example.Banking_Mangment.Entity;

import com.example.Banking_Mangment.Entity.Type.InsuranceStatus;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class InsuranceSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double coverageAmount;

    private Double premiumAmount;

    @Enumerated(EnumType.STRING)
    private InsuranceType type;
}