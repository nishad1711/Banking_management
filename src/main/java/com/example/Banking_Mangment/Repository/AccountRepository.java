package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByPhoneNumber(String senderPhone);
    List<Account> findByPersonUserId(Long userId);
}