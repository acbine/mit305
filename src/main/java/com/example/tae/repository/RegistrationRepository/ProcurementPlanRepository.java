package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProcurementPlanRepository extends JpaRepository<ProcurementPlan, Integer> {

    List<ProcurementPlan> findAllByProjectPlan_Id(int prjId);

    @Query(value = "SELECT * FROM tae.procurement_plan where purchase_ordercode IS Null", nativeQuery = true)
    List<ProcurementPlan> findByProcurementPlanState();

}
