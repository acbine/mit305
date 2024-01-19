package com.example.tae.ReceivingRepositoyTest;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.service.BinService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class receivecingTest {

    @Autowired
    ReceivingProcessingRepository receivingProcessingRepository;

    @Autowired
    BinService binServices;

    @Test
    @Transactional
    public void aa(){

        List<ProcurementPlan> aalist= receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST();

        //aalist.forEach(x-> System.out.println("---------------------------"+x));
        System.out.println(aalist.get(0).getProductForProject());
        //aalist.get(1).getProductForProject()

    }

    @Test
    public void bb(){
        System.out.println("시작");
        binServices.procurementPlanList();

    }

    @Test
    @Transactional
    public void bbss(){

        receivingProcessingRepository.findByProcumentPlanCode(2);
        System.out.println(receivingProcessingRepository.findByProcumentPlanCode(2).getProcurementPlan());


    }


}
