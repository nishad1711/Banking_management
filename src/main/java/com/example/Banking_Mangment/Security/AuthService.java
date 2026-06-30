package com.example.Banking_Mangment.Security;

import com.example.Banking_Mangment.Dto.AuthResponse;
import com.example.Banking_Mangment.Dto.LoginRequest;
import com.example.Banking_Mangment.Dto.SignupRequest;
import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Entity.Person;
import com.example.Banking_Mangment.Repository.AccountRepository;
import com.example.Banking_Mangment.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    public AuthResponse signup(SignupRequest request) {
        List<Account> accounts =
                accountRepository.findAllByPhoneNumber(
                        request.getPhoneNumber());

        if (accounts.isEmpty()) {
            throw new RuntimeException(
                    "No bank account linked with this phone number");
        }

        if (personRepository.existsByPhoneNumber(
                request.getPhoneNumber())) {

            throw new RuntimeException(
                    "Phone Number already registered");
        }

        Person person = new Person();

        person.setName(request.getName());
        person.setEmail(request.getEmail());
        person.setPhoneNumber(request.getPhoneNumber());

        person.setPassword(
                passwordEncoder.encode(request.getPassword()));

        person.setPin(
                passwordEncoder.encode(request.getPin()));

        person.setCreated_at(LocalTime.now());
        person.setCreated_at_d(LocalDate.now());

        personRepository.save(person);
        for (Account account : accounts) {
            account.setPerson(person);
        }

        accountRepository.saveAll(accounts);

        return new AuthResponse(
                "User registered successfully",
                null
        );
    }

    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getPhoneNumber(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(userDetails);

        return new AuthResponse(
                "Login Successful",
                token
        );
    }
}