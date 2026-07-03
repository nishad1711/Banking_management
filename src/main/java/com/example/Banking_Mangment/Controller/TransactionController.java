package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Dto.ChangePrimaryAccountDto;
import com.example.Banking_Mangment.Dto.PersonTransactionalHistoryDto;
import com.example.Banking_Mangment.Dto.TransactionTransferDto;
import com.example.Banking_Mangment.Dto.TransactiondetailsDto;
import com.example.Banking_Mangment.Service.PinVerificationService;
import com.example.Banking_Mangment.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final PinVerificationService pinVerificationService;

    @PostMapping("/transfermoney")
    public ResponseEntity<TransactiondetailsDto> sentmoney(
            @RequestBody TransactionTransferDto transactionTransferDto,
            @RequestHeader("X-PIN") String pin) {

        pinVerificationService.verifyPin(pin);

        return ResponseEntity.ok(
                transactionService.transferMoney(transactionTransferDto));
    }

    @GetMapping("/history")
    public ResponseEntity<List<PersonTransactionalHistoryDto>> getHistory(
            Authentication authentication,
            @RequestHeader("X-PIN") String pin) {

        pinVerificationService.verifyPin(pin);

        return ResponseEntity.ok(
                transactionService.personTransactionalHistory(
                        authentication.getName()));
    }

    @PutMapping("/change-primary-account")
    public ResponseEntity<String> changePrimaryAccount(
            @RequestBody ChangePrimaryAccountDto dto,
            @RequestHeader("X-PIN") String pin) {

        pinVerificationService.verifyPin(pin);

        transactionService.changePrimaryAccount(dto);

        return ResponseEntity.ok("Primary account updated successfully");
    }
}