package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.Account;
import com.example.Banking_Mangment.Entity.Insurance;
import com.example.Banking_Mangment.Entity.InsuranceSchema;
import com.example.Banking_Mangment.Entity.Type.InsuranceStatus;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import com.example.Banking_Mangment.Exception.AccountNotFoundException;
import com.example.Banking_Mangment.Exception.InsurancePlanNotFoundException;
import com.example.Banking_Mangment.Repository.AccountRepository;
import com.example.Banking_Mangment.Repository.InsuranceRepository;
import com.example.Banking_Mangment.Repository.InsuranceSchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceSchemaRepository insuranceSchemaRepository;
    private final InsuranceRepository insuranceRepository;
    private final AccountRepository accountRepository;

    public Page<InsuranceSchema> showInsuranceDetails(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return insuranceSchemaRepository.findAll(pageable);
    }

    public List<InsuranceSchema> getinsurancebykeyword(String keyword) {

        return insuranceSchemaRepository.searchInsurance(keyword);
    }

    public List<InsuranceSchema> filterInsurance(
            InsuranceType type,
            Double coverageAmount,
            Double premiumAmount
    ) {

        return insuranceSchemaRepository.filterInsurance(
                type,
                coverageAmount,
                premiumAmount
        );
    }

    public Insurance applyInsurance(
            Long accountId,
            Long insuranceSchemaId
    ) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));

        InsuranceSchema insuranceSchema =
                insuranceSchemaRepository.findById(insuranceSchemaId)
                        .orElseThrow(() ->
                                new InsurancePlanNotFoundException(
                                        "Insurance Plan not found"));

        Insurance insurance = new Insurance();

        insurance.setAccount(account);
        insurance.setInsuranceSchema(insuranceSchema);
        insurance.setStatus(InsuranceStatus.ACTIVE);

        return insuranceRepository.save(insurance);
    }
}