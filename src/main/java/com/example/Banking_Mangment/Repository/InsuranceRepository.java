package com.example.Banking_Mangment.Repository;

import com.example.Banking_Mangment.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}