package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository
        extends JpaRepository<BlacklistedToken, String> {

    boolean existsByToken(String token);
}