package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}