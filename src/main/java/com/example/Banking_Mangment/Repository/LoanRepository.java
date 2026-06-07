package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}