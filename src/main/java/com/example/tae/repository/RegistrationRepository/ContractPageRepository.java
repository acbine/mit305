package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.Contract.ContractPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractPageRepository extends JpaRepository<ContractPage, Integer> {

    @Query(value = "SELECT cp.contract_page_code, cp.contract_name, cp.contract_contract_code, cp.contract_write_date  FROM contract_page cp " +
            "JOIN contract c ON cp.contract_contract_code = c.contract_code " +
            "WHERE c.company_business_number = :businessNumber", nativeQuery = true)
    List<ContractPage> findContractCodes_of_businessNumber(@Param("businessNumber") String businessNumber);

}
