package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // Get all accounts of the logged-in user
    @GetMapping("/myaccounts")
    public ResponseEntity<List<Account>> getMyAccounts(
            Authentication authentication) {

        return ResponseEntity.ok(
                accountService.getMyAccounts(authentication.getName()));
    }

    // Get current primary account
    @GetMapping
    public ResponseEntity<Account> getCurrentAccount(
            Authentication authentication) {

        return ResponseEntity.ok(
                accountService.getCurrentAccount(authentication.getName()));
    }
}
