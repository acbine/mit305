package com.example.tae.repository;

import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReleaseProcess.Existence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExistenceRepository extends JpaRepository<Existence, Integer> {
   @Query("select e from Existence e" +
           " where e.productCode= :code "+
           " order by e.modDate desc ")
    Optional<Existence> findByProductCode(@Param("code") ProductInformationRegistration code);

   @Query("select e from Existence  e "+
            " where e.productCode.product_name = :productName ")
    Existence findByProductName(@Param("productName") String productName);
}
