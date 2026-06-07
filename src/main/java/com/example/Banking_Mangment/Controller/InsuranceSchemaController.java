package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Entity.Insurance;
import com.example.Banking_Mangment.Entity.InsuranceSchema;
import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import com.example.Banking_Mangment.Service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Insurance")
@RequiredArgsConstructor
public class InsuranceSchemaController {

    private final InsuranceService insuranceService;

    @GetMapping("/AvailableInsurance")
    public ResponseEntity<Page<InsuranceSchema>> getAvailableInsurance(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                insuranceService.showInsuranceDetails(page, size)
        );
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<InsuranceSchema>> getInsuranceByKeyword(
            @PathVariable String keyword) {

        return ResponseEntity.ok(
                insuranceService.getinsurancebykeyword(keyword)
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<List<InsuranceSchema>> filterInsurance(

            @RequestParam(required = false) InsuranceType type,
            @RequestParam(required = false) Double coverageAmount,
            @RequestParam(required = false) Double premiumAmount
    ) {

        return ResponseEntity.ok(
                insuranceService.filterInsurance(
                        type,
                        coverageAmount,
                        premiumAmount
                )
        );
    }
    @PostMapping("/apply")
    public ResponseEntity<Insurance> applyInsurance(

            @RequestParam Long accountId,

            @RequestParam Long insuranceSchemaId
    ) {

        return ResponseEntity.ok(
                insuranceService.applyInsurance(
                        accountId,
                        insuranceSchemaId
                )
        );
    }
}