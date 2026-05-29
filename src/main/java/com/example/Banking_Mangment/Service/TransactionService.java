package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Dto.TransactionTransferDto;
import com.example.Banking_Mangment.Dto.TransactiondetailsDto;
import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Entity.Transaction;
import com.example.Banking_Mangment.Repository.AccountRepository;
import com.example.Banking_Mangment.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Transactional // CRITICAL: Ensures the database rolls back if an error occurs mid-transfer
    public TransactiondetailsDto transferMoney(TransactionTransferDto transactionTransferDto) {

        // 1. Prevent sending money to yourself
        if (transactionTransferDto.getReceiverPhone().equals(transactionTransferDto.getSenderPhone())) {
            throw new RuntimeException("Cannot transfer money to your own account");
        }

        // 2. Prevent negative or zero transfers
        if (transactionTransferDto.getAmount() <= 0) {
            throw new RuntimeException("Transfer amount must be greater than zero");
        }

        // 3. Fetch Sender Account
        Account sender = accountRepository.findByPhoneNumber(transactionTransferDto.getSenderPhone())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        // 4. Fetch Receiver Account
        Account receiver = accountRepository.findByPhoneNumber(transactionTransferDto.getReceiverPhone())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        // 5. Check if Sender has enough money
        if (sender.getBalance() < transactionTransferDto.getAmount()) {
            throw new RuntimeException("Insufficient balance for transfer");
        }

        // 6. Perform the math
        sender.setBalance(sender.getBalance() - transactionTransferDto.getAmount());
        receiver.setBalance(receiver.getBalance() + transactionTransferDto.getAmount());

        // 7. Save both accounts to the database
        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = new Transaction();
        transaction.setSender_id(sender.getAccount_id());
        transaction.setReceiver_id(receiver.getAccount_id());
        transaction.setAmount(transactionTransferDto.getAmount());
        transaction.setStatus(true);
        transaction.setSending_time(LocalTime.now());
        transaction.setSending_date(LocalDate.now());

        transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactiondetailsDto.class);
    }
}