package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Entity.Person;
import com.example.Banking_Mangment.Repository.AccountRepository;
import com.example.Banking_Mangment.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountrepo;
    private final PersonRepository personRepository;

    // Returns all accounts of the logged-in user
    public List<Account> getMyAccounts(String phoneNumber) {

        Person person = personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return accountrepo.findByPersonUserId(person.getUserId());
    }

    // Returns a specific account only if it belongs to the logged-in user
    public Account findMyAccount(Long accountId, String phoneNumber) {

        Person person = personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Account> accounts =
                accountrepo.findByPersonUserId(person.getUserId());

        for (Account account : accounts) {

            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }

        throw new RuntimeException(
                "This account does not belong to the logged-in user");
    }
}