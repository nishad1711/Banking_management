package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.LoanSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface LoanSchemaRepository extends JpaRepository<LoanSchema, Long> {
}