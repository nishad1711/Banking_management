package com.example.Banking_Mangment.Entity;

import com.example.Banking_Mangment.Entity.Type.InsuranceStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account account;

    @ManyToOne
    @JoinColumn(name = "insurance_schema_id")
    private InsuranceSchema insuranceSchema;
}