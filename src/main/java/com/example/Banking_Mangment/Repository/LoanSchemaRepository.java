package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.LoanSchema;
import com.example.Banking_Mangment.Entity.Type.LoanProviderType;
import com.example.Banking_Mangment.Entity.Type.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanSchemaRepository extends JpaRepository<LoanSchema, Long> {
    @Query("""
       SELECT l
       FROM LoanSchema l
       WHERE LOWER(str(l.loanType)) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(str(l.providerType)) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    List<LoanSchema> searchLoan(@Param("keyword") String keyword);
    @Query("""
        SELECT l
        FROM LoanSchema l
        WHERE (:loanType IS NULL OR l.loanType = :loanType)
          AND (:providerType IS NULL OR l.providerType = :providerType)
          AND (:maxAmount IS NULL OR l.maxAmount <= :maxAmount)
          AND (:interestRate IS NULL OR l.interestRate <= :interestRate)
          AND (:tenureMonths IS NULL OR l.tenureMonths <= :tenureMonths)
          AND l.active = true
        """)
    List<LoanSchema> filterLoans(
            @Param("loanType") LoanType loanType,
            @Param("providerType") LoanProviderType providerType,
            @Param("maxAmount") Double maxAmount,
            @Param("interestRate") Double interestRate,
            @Param("tenureMonths") Integer tenureMonths
    );
}