package com.example.Banking_Mangment.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Account {
    @Id
    private Long account_id;

    @Column(name = "phone no.", unique = true, nullable = false)
    @Pattern(regexp = "^\\d{10}$", message = "Value must be exactly 10 digits")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;

    private double balance;

    private LocalTime created_at;

    @OneToMany(mappedBy = "account")
    private List<Loan> loans = new ArrayList<>();
    @OneToMany(mappedBy = "account")
    private List<Insurance> insurances = new ArrayList<>();


}
