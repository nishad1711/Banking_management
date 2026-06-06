package com.example.Banking_Mangment.Service;

import com.example.Banking_Mangment.Entity.InsuranceSchema;
import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import com.example.Banking_Mangment.Repository.InsuranceSchemaRepository;
import com.example.Banking_Mangment.Repository.LoanSchemaRepository;
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

    public Page<InsuranceSchema> showLoanDetails(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return insuranceSchemaRepository.findAll(pageable);
    }
    public List<InsuranceSchema> getinsurancebykeyword(String keyword) {
        List<InsuranceSchema> insurances= insuranceSchemaRepository.searchInsurance(keyword);
        return insurances ;
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

}
