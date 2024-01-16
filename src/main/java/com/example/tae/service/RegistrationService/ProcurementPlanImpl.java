package com.example.tae.service.RegistrationService;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcurementPlanImpl implements ProcurementPlanService{

    @Autowired
    ProcurementPlanRepository planRepository;

//    @Override
//    public void insert_plan() {
//
//        ProcurementPlan procurementPlan = ProcurementPlan.builder().plan_code(1001).build();
//
//        planRepository.save(procurementPlan);
//
//    }
}
