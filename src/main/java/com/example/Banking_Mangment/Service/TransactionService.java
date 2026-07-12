package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Dto.ChangePrimaryAccountDto;
import com.example.Banking_Mangment.Dto.PersonTransactionalHistoryDto;
import com.example.Banking_Mangment.Dto.TransactionTransferDto;
import com.example.Banking_Mangment.Dto.TransactiondetailsDto;
import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Entity.Person;
import com.example.Banking_Mangment.Entity.Transaction;
import com.example.Banking_Mangment.Exception.AccountNotFoundException;
import com.example.Banking_Mangment.Exception.InsufficientBalanceException;
import com.example.Banking_Mangment.Exception.InvalidTransactionException;
import com.example.Banking_Mangment.Exception.PrimaryAccountNotFoundException;
import com.example.Banking_Mangment.Exception.UnauthorizedAccountAccessException;
import com.example.Banking_Mangment.Exception.UserNotFoundException;
import com.example.Banking_Mangment.Repository.AccountRepository;
import com.example.Banking_Mangment.Repository.PersonRepository;
import com.example.Banking_Mangment.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;

    @Transactional
    public TransactiondetailsDto transferMoney(TransactionTransferDto transactionTransferDto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String senderPhone = authentication.getName();

        // Prevent sending money to yourself
        if (transactionTransferDto.getReceiverPhone().equals(senderPhone)) {
            throw new InvalidTransactionException(
                    "Cannot transfer money to your own account");
        }

        // Prevent negative or zero transfers
        if (transactionTransferDto.getAmount() <= 0) {
            throw new InvalidTransactionException(
                    "Transfer amount must be greater than zero");
        }

        // Fetch Sender Primary Account
        Account sender = accountRepository
                .findByPhoneNumberAndPrimaryAccountTrue(senderPhone)
                .orElseThrow(() ->
                        new PrimaryAccountNotFoundException(
                                "Sender primary account not found"));

        // Fetch Receiver Primary Account
        Account receiver = accountRepository
                .findByPhoneNumberAndPrimaryAccountTrue(
                        transactionTransferDto.getReceiverPhone())
                .orElseThrow(() ->
                        new PrimaryAccountNotFoundException(
                                "Receiver primary account not found"));

        // Check balance
        if (sender.getBalance() < transactionTransferDto.getAmount()) {
            throw new InsufficientBalanceException(
                    "Insufficient balance for transfer");
        }

        // Debit sender
        sender.setBalance(
                sender.getBalance() - transactionTransferDto.getAmount());

        // Credit receiver
        receiver.setBalance(
                receiver.getBalance() + transactionTransferDto.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setSenderId(sender.getAccountId());
        transaction.setReceiverId(receiver.getAccountId());
        transaction.setAmount(transactionTransferDto.getAmount());
        transaction.setStatus(true);
        transaction.setSending_time(LocalTime.now());
        transaction.setSending_date(LocalDate.now());

        transactionRepository.save(transaction);

        return modelMapper.map(transaction, TransactiondetailsDto.class);
    }

    public List<PersonTransactionalHistoryDto> personTransactionalHistory(String phoneNumber) {

        Person person = personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Long userId = person.getUserId();

        List<Account> accounts =
                accountRepository.findByPersonUserId(userId);

        List<PersonTransactionalHistoryDto> history =
                new ArrayList<>();

        for (Account account : accounts) {

            List<Transaction> transactions =
                    transactionRepository.findBySenderId(account.getAccountId());

            for (Transaction tx : transactions) {

                Account receiverAccount =
                        accountRepository.findById(tx.getReceiverId())
                                .orElseThrow(() ->
                                        new AccountNotFoundException(
                                                "Receiver account not found"));

                String receiverName;

                if (receiverAccount.getPerson() != null) {
                    receiverName = receiverAccount.getPerson().getName();
                } else {
                    receiverName = "Not Registered";
                }

                PersonTransactionalHistoryDto dto =
                        new PersonTransactionalHistoryDto(
                                tx.getAmount(),
                                tx.getSending_time(),
                                tx.getSending_date(),
                                tx.isStatus(),
                                receiverName,
                                tx.getSenderId()
                        );

                history.add(dto);
            }
        }

        return history;
    }

    @Transactional
    public void changePrimaryAccount(ChangePrimaryAccountDto dto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String phoneNumber = authentication.getName();

        Person person = personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        List<Account> accounts =
                accountRepository.findByPersonUserId(person.getUserId());

        Account selectedAccount = null;

        for (Account account : accounts) {

            if (account.getAccountId().equals(dto.getAccountId())) {
                selectedAccount = account;
            }

            account.setPrimaryAccount(false);
        }

        if (selectedAccount == null) {
            throw new UnauthorizedAccountAccessException(
                    "This account does not belong to the logged-in user");
        }

        selectedAccount.setPrimaryAccount(true);

        accountRepository.saveAll(accounts);
    }
}