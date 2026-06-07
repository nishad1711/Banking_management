package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Entity.Loan;
import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.LoanProviderType;
import com.example.Banking_Mangment.Entity.Type.LoanType;
import com.example.Banking_Mangment.Service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan-schema")
@RequiredArgsConstructor
public class LoanSchemaController {

    private final LoanService loanService;

    @GetMapping("/available")
    public ResponseEntity<Page<LoanSchema>> getLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                loanService.showLoanDetails(page, size)
        );
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<LoanSchema>> getLoanByKeyword(
            @PathVariable String keyword) {

        return ResponseEntity.ok(
                loanService.getloanbykeyword(keyword)
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LoanSchema>> filterLoans(

            @RequestParam(required = false) LoanType loanType,
            @RequestParam(required = false) LoanProviderType providerType,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) Double interestRate,
            @RequestParam(required = false) Integer tenureMonths
    ) {

        return ResponseEntity.ok(
                loanService.filterLoans(
                        loanType,
                        providerType,
                        maxAmount,
                        interestRate,
                        tenureMonths
                )
        );
    }
    @PostMapping("/apply")
    public ResponseEntity<Loan> applyLoan(

            @RequestParam Long accountId,

            @RequestParam Long loanSchemaId,

            @RequestParam Double amount
    ) {

        return ResponseEntity.ok(
                loanService.applyLoan(
                        accountId,
                        loanSchemaId,
                        amount
                )
        );
    }
}