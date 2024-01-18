package com.example.tae.repository.RegistrationRepository;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProcurementPlanRepository extends JpaRepository<ProcurementPlan, Integer> {
    List<ProcurementPlan> findAllByProjectPlan_Id(int prjId);



}
