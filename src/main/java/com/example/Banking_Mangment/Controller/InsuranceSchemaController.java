package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Entity.InsuranceSchema;
import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import com.example.Banking_Mangment.Service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Insurance")
@RequiredArgsConstructor
public class InsuranceSchemaController {
    private final InsuranceService insuranceService;
    @GetMapping("/AvailableInsurance")
    public ResponseEntity<Page<InsuranceSchema>> getLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                insuranceService.showLoanDetails(page, size)
        );
    }
    @GetMapping("/searchinsurance/keyword")
    public ResponseEntity<List<InsuranceSchema>> getproductbykeyword(@RequestParam("keyword") String keyword){
        List<InsuranceSchema> insurance = insuranceService.getinsurancebykeyword(keyword);
        if(insurance == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<InsuranceSchema>> filterInsurance(

            @RequestParam(required = false)
            InsuranceType type,

            @RequestParam(required = false)
            Double coverageAmount,

            @RequestParam(required = false)
            Double premiumAmount
    ) {

        List<InsuranceSchema> insurances =
                insuranceService.filterInsurance(
                        type,
                        coverageAmount,
                        premiumAmount
                );

        return ResponseEntity.ok(insurances);
    }
}
