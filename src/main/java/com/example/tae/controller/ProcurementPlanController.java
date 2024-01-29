package com.example.tae.controller;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.ProjectRepository.ProjectPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.service.RegistrationService.ProcurementPlanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProcurementPlanController {

    // 조달 계획 페이지 요청
    @GetMapping("ProcurementPlanRegistration")
    public String ProcurementPlanRegistration() {
        return "ProcurementPlanRegistration";
    }

    @Autowired
    ProcurementPlanImpl procurementPlanService;

    @Autowired
    private ProjectPlanRepository projectPlanRepository;

    @Autowired
    private ProcurementPlanRepository procurementPlanRepository;

    // 발주 등록 처리
    @PostMapping("/plan_edit/{procurement_plan_code}")
    @ResponseBody
    public String updateProcurementPlan(@PathVariable(value = "procurement_plan_code") int procurement_plan_code) {

        ProcurementPlan procurementPlan = procurementPlanRepository.findById(procurement_plan_code).orElse(null);

        procurementPlan.setOrder_state("발주전");

        procurementPlanRepository.save(procurementPlan);

        return "발주 등록한 조달 계획: " + procurement_plan_code;
    }

    // 조달 계획 삭제
    @PostMapping("/plan_delete/{procurement_plan_code}")
    @ResponseBody
    public String deletePlan(@PathVariable(value = "procurement_plan_code") int procurement_plan_code) {

        procurementPlanRepository.deleteById(procurement_plan_code);

        return "삭제한 조달 계획 코드: " + procurement_plan_code;
    }


}
