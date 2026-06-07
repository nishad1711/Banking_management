package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Entity.Loan;
import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.LoanProviderType;
import com.example.Banking_Mangment.Entity.Type.LoanStatus;
import com.example.Banking_Mangment.Entity.Type.LoanType;
import com.example.Banking_Mangment.Repository.AccountRepository;
import com.example.Banking_Mangment.Repository.LoanRepository;
import com.example.Banking_Mangment.Repository.LoanSchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanSchemaRepository loanSchemaRepository;
    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;

    public Page<LoanSchema> showLoanDetails(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return loanSchemaRepository.findAll(pageable);
    }
    public List<LoanSchema> getloanbykeyword(String keyword) {
        List<LoanSchema> Loans= loanSchemaRepository.searchLoan(keyword);
        return Loans;
    }
    public List<LoanSchema> filterLoans(
            LoanType loanType,
            LoanProviderType providerType,
            Double maxAmount,
            Double interestRate,
            Integer tenureMonths
    ) {

        return loanSchemaRepository.filterLoans(
                loanType,
                providerType,
                maxAmount,
                interestRate,
                tenureMonths
        );
    }
    public Loan applyLoan(
            Long accountId,
            Long loanSchemaId,
            Double amount
    ) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        LoanSchema loanSchema =
                loanSchemaRepository.findById(loanSchemaId)
                        .orElseThrow(() ->
                                new RuntimeException("Loan Scheme not found"));

        if(amount > loanSchema.getMaxAmount()){
            throw new RuntimeException(
                    "Amount exceeds scheme limit");
        }

        Loan loan = new Loan();

        loan.setAccount(account);
        loan.setLoanSchema(loanSchema);
        loan.setAmount(amount);
        loan.setStatus(LoanStatus.APPLIED);

        return loanRepository.save(loan);
    }
}