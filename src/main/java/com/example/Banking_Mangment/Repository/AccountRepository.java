package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}