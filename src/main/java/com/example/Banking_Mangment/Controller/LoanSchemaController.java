package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.LoanProviderType;
import com.example.Banking_Mangment.Entity.Type.LoanType;
import com.example.Banking_Mangment.Service.LoanService;
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
@RequestMapping("/loan-schema")
@RequiredArgsConstructor
public class LoanSchemaController {

    private final LoanService loanService;
    //using pagination we get first 5 elemtn by defauls than accoridng to front end it give next request from 1 to 5 which give later part url for that
    //http://localhost:8080/api/v1/loan-schema/AvailableLoans?page=1&size=5 //request by fronend
    @GetMapping("/AvailableLoans")
    public ResponseEntity<Page<LoanSchema>> getLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                loanService.showLoanDetails(page, size)
        );
    }
    @GetMapping("/searchloan/keyword")
    public ResponseEntity<List<LoanSchema>> getproductbykeyword(@RequestParam("keyword") String keyword){
        List<LoanSchema> loan = loanService.getloanbykeyword(keyword);
        if(loan == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<LoanSchema>> filterLoans(

            @RequestParam(required = false) LoanType loanType,

            @RequestParam(required = false)
            LoanProviderType providerType,

            @RequestParam(required = false)
            Double maxAmount,

            @RequestParam(required = false)
            Double interestRate,

            @RequestParam(required = false)
            Integer tenureMonths
    ) {

        List<LoanSchema> loans =
                loanService.filterLoans(
                        loanType,
                        providerType,
                        maxAmount,
                        interestRate,
                        tenureMonths
                );

        return ResponseEntity.ok(loans);
    }
}
