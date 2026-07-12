package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.Person;
import com.example.Banking_Mangment.Exception.InvalidPinException;
import com.example.Banking_Mangment.Exception.UserNotFoundException;
import com.example.Banking_Mangment.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PinVerificationService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public void verifyPin(String pin) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String phoneNumber = authentication.getName();

        Person person = personRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(pin, person.getPin())) {
            throw new InvalidPinException("Invalid PIN");
        }
    }
}