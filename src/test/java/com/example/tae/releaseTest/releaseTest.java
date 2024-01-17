package com.example.tae.releaseTest;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;

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
            ReleaseProcess releaseProcess = releaseProcessService.release(value);
            System.out.println(releaseProcess);
        });
    }

    //가장 최신값 가져오기
    @Test
    public void recentData() {
        System.out.println("가장 최근의 출고 값이 맞는지 확인 : "+releaseRepository.findTop1ByOrderByModDateDesc().getRegDate());
    }

    @Test
    public void findProceure() {
            for(ProcurementPlan procurementPlan : procurementPlanRepository.findAllByProjectPlan_Id(1)) {
                Optional<Contract> contractOptional = contractRepository.findById(procurementPlan.getContract().getContract_code());
                System.out.println(contractOptional.get().getProductInformationRegistration().getProduct_name());
            };
    }
}
