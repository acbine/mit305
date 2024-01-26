package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProcurementPlan.dto.ProcurementPlanDto;
import com.example.tae.entity.ProcurementPlan.dto.ProcurementPlanJoinDTO;
import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.OrderRepository;
import com.example.tae.repository.ProductForProjectRepository;
import com.example.tae.repository.ProjectRepository.ProjectPlanRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import com.example.tae.service.RegistrationService.ProcurementPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProcurementPlanRestController  {

    private ProcurementPlanRepository procurementPlanRepository;

    private ProcurementPlanService procurementPlanService;

    private ProjectPlanRepository projectPlanRepository;

    private ProjectRepository projectRepository;

    private ContractRepository contractRepository;

    private OrderRepository purchaseRepository;

    @GetMapping("/search/Project_plan")
    public List<ProcurementPlanJoinDTO> GetJoin(@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        List<ProcurementPlanJoinDTO> results = procurementPlanService.getJoin(startDate, endDate);

        return results;
    }

    @GetMapping("/search/procurementPlan")
    @ResponseBody
    public List<ProcurementPlan> getAllPlan() {

        return procurementPlanService.getAllPlan();
    }

    @PostMapping("/procurementPlan/register")
    public void ProcurementPlanRegister(@RequestBody List<ProcurementPlanDto> procurementPlanDtoList) {

        for(ProcurementPlanDto data : procurementPlanDtoList) {

//            // 생산 계획 id 검사
            ProjectPlan project = projectPlanRepository.findById(data.getId()).orElse(null);
//            System.out.println("등록한 생산 계획 id :" + project);
//
//            // 계약 코드 검사
            Contract contract = contractRepository.findById(data.getContract_code()).orElse(null);
//            System.out.println("등록한 계약 코드 : " + contract);
//
//            // 제품명 검사
            Project project_Name = projectRepository.findById(data.getProject_name()).orElse(null);
            System.out.println("등록한 제품명: " + project_Name);
//
//            // 발주서 코드
            Purchase purchase_code = purchaseRepository.findById(data.getOrdercode()).orElse(null);
            System.out.println("초기엔 발주서 코드가 None : " + purchase_code);
//
//            // 발주일
            System.out.println("등록한 발주일 : " + data.getOrder_date());

            System.out.println( "조달 총 수량 : " + data.getSupportProductAmount());

            // 발주 상태
            System.out.println("등록한 발주 상태 : " + data.getOrder_state());

            // 등록
            if(project != null && contract != null) {

                ProcurementPlan procurementPlan = new ProcurementPlan();

                procurementPlan.setProjectPlan(project); // 생산 계획 코드
                procurementPlan.setContract(contract); // 계약 코드
                procurementPlan.setProject(project_Name); // 조달 제품명
                procurementPlan.setSupportProductAmount(data.getSupportProductAmount());// 조달 수량
                procurementPlan.setOrder_date(data.getOrder_date()); // 발주일

                procurementPlanRepository.save(procurementPlan);
            }

        }

    }

}