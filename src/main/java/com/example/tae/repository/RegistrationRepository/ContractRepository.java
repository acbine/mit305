package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("select pl  from ProcurementPlan pl, Contract  c " +
            "where c.productInformationRegistration.product_code = :productId" +
            " and pl.contract.contract_code = c.contract_code" +
            " and pl.order_state ='마감'")
    List<ProcurementPlan> findByproductInformationId(@Param("productId") int productId);

    @Query("select c from Contract  c " +
            " where c.productInformationRegistration = :productName ")
    List<Contract> findByProductInformationRegistrationCode(@Param("productName") ProductInformationRegistration productName);

}
