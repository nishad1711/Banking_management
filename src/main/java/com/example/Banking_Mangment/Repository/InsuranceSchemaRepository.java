package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.InsuranceSchema;
import com.example.Banking_Mangment.Entity.Type.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceSchemaRepository extends JpaRepository<InsuranceSchema, Long> {
    @Query("""
       SELECT i
       FROM InsuranceSchema i
       WHERE LOWER(str(i.type)) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(str(i.status)) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    List<InsuranceSchema> searchInsurance(@Param("keyword") String keyword);

    @Query("""
        SELECT i
        FROM InsuranceSchema i
        WHERE (:type IS NULL OR i.type = :type)
          AND (:coverageAmount IS NULL OR i.coverageAmount <= :coverageAmount)
          AND (:premiumAmount IS NULL OR i.premiumAmount <= :premiumAmount)
        """)
    List<InsuranceSchema> filterInsurance(
            @Param("type") InsuranceType type,
            @Param("coverageAmount") Double coverageAmount,
            @Param("premiumAmount") Double premiumAmount
    );
}