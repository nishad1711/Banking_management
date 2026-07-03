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

    // Returns the current primary account of the logged-in user
    public Account getCurrentAccount(String phoneNumber) {

        return accountrepo
                .findByPhoneNumberAndPrimaryAccountTrue(phoneNumber)
                .orElseThrow(() ->
                        new RuntimeException("Primary account not found"));
    }
}