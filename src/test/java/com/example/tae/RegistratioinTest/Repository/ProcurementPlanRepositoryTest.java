package com.example.tae.RegistratioinTest.Repository;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.ProductRepository.ProjectPlanRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ProcurementPlanRepositoryTest {

    @Autowired // 생산 계획
    ProjectPlanRepository projectPlanRepository;


    @Autowired // 계약
    ContractRepository contractRepository;

    @Autowired // 조달 계획
    ProcurementPlanRepository procurementPlanRepository;

    Date date1 = new Date(2024,01,18);

    @Test
    public void Test1() {

        // 생산계획 아이디
        ProjectPlan projectPlan = projectPlanRepository.findById(2).get();

        // 계약 코드
        Contract contract = contractRepository.findById(2).get();

        ProcurementPlan procurementPlan = ProcurementPlan.builder()
                .projectPlan(projectPlan)
                .contract(contract)
                .product_amount(8)
                .order_date(date1)
                .order_state("발주 안됨")
                .build();

        procurementPlanRepository.save(procurementPlan);

    }
}
