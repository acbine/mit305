package com.example.tae.controller;

import com.example.tae.repository.ProjectRepository.ProjectPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.service.RegistrationService.ProcurementPlanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


}
