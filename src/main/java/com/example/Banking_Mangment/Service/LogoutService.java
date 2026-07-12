package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.BlacklistedToken;
import com.example.Banking_Mangment.Repository.BlacklistedTokenRepository;
import com.example.Banking_Mangment.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final BlacklistedTokenRepository repository;
    private final JwtService jwtService;

    public void logout(String token) {

        BlacklistedToken blacklistedToken = new BlacklistedToken();

        blacklistedToken.setToken(token);
        blacklistedToken.setExpiryDate(jwtService.extractExpiration(token));

        repository.save(blacklistedToken);
    }
}