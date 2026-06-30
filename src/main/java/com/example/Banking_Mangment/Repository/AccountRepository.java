package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByPhoneNumber(String phoneNumber);

    Optional<Account> findByPhoneNumber(String phoneNumber);

    List<Account> findByPersonUserId(Long userId);

    Optional<Account> findByPhoneNumberAndPrimaryAccountTrue(String phoneNumber);

    Optional<Account> findByAccountId(Long accountId);

}