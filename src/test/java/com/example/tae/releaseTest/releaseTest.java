package com.example.tae.releaseTest;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;

import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.ReleaseRepository;
import com.example.tae.service.ReleaseProcessServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class releaseTest {
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ReleaseProcessServiceImpl releaseProcessService;
    @Autowired
    private ProcurementPlanRepository procurementPlanRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Test
    public void entityCreateTest() {
        IntStream.rangeClosed(1,50).forEach(value -> {
            ReleaseDto releaseProcess = releaseProcessService.release(value,value);
            System.out.println(releaseProcess);
        });
    }

    @Test
    public void findProceure() {
            for(ProcurementPlan procurementPlan : procurementPlanRepository.findAllByProjectPlan_Id(1)) {
                Optional<Contract> contractOptional = contractRepository.findById(procurementPlan.getContract().getContract_code());
                System.out.println(contractOptional.get().getProductInformationRegistration().getProduct_name());
            };
    }
}
