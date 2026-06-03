package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Repository.LoanSchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanSchemaRepository loanSchemaRepository;

    public Page<LoanSchema> showLoanDetails(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return loanSchemaRepository.findAll(pageable);
    }
}