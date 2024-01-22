package com.example.tae.service.RegistrationService;


import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementPlanImpl implements ProcurementPlanService{

    @Autowired
    private ProcurementPlanRepository procurementPlanRepository;

    @Override
    public List<ProcurementPlan> getAllPlan() {
        return procurementPlanRepository.findAll();
    }


//    @Override
//    public void insert_plan() {
//
//        ProcurementPlan procurementPlan = ProcurementPlan.builder().plan_code(1001).build();
//
//        planRepository.save(procurementPlan);
//
//    }
}
