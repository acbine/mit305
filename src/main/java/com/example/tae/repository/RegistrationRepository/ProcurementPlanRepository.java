package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProcurementPlanRepository extends JpaRepository<ProcurementPlan, Integer> {

    List<ProcurementPlan> findAllByProjectPlan_Id(int prjId);

    @Query(value = "SELECT * FROM tae.procurement_plan where purchase_ordercode IS Null", nativeQuery = true)
    List<ProcurementPlan> findByProcurementPlanState();


    @Query("select pr from ProcurementPlan pr " +
        " where pr.contract.contract_code = :contractCode")
    ProcurementPlan findByContract_Contract_code(@Param("contractCode") int contractCode);

}
