package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.ProjectRepository.ProjectPlanRepository;
import com.example.tae.service.RegistrationService.ProcurementPlanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class ProcurementPlanController {

    // 조달 계획 페이지 요청
    @GetMapping("ProcurementPlanRegistration")
    public String ProcurementPlanRegistration() {
        return "ProcurementPlanRegistration";
    }

    @Autowired
    ProcurementPlanImpl procurementPlan;

    @Autowired
    private ProjectPlanRepository projectPlanRepository;

    @PostMapping("/asd")
    @ResponseBody
    public ResponseEntity<Contract> asd() {

        Contract contract = new Contract();

        return ResponseEntity.status(HttpStatus.OK).body(contract);
    }

    @GetMapping("/search/procurementPlan")
    @ResponseBody
    public List<ProcurementPlan> planAll() {

        return procurementPlan.getAllPlan();
    }

    @GetMapping("/search/Project_plan")
    @ResponseBody
    public List<ProjectPlan> searchDate(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    )
    {
        List<ProjectPlan> projectPlans = projectPlanRepository.findByDateBetween(startDate, endDate);

        return projectPlans;
    }

}
