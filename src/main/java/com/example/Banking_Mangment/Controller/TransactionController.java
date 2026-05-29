package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Dto.TransactionTransferDto;
import com.example.Banking_Mangment.Dto.TransactiondetailsDto;
import com.example.Banking_Mangment.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transfermoney")
    public ResponseEntity<TransactiondetailsDto> sentmoney(@RequestBody TransactionTransferDto transactionTransferDto) {
       return ResponseEntity.ok(transactionService.transferMoney(transactionTransferDto));
    }

}
