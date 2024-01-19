package com.example.tae.ReceivingRepositoyTest;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.service.BinService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Test
    public void asdas(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date changedStartDate; //Date 타입 시작 날짜
            Date changedEndDate; //Date 타입 끝날자

            changedStartDate = sdf.parse("1000-12-31");
            changedEndDate = sdf.parse("5000-12-31");
            List<Object[]> resultList = receivingProcessingRepository.groupByOrderState (changedStartDate, changedEndDate);

            for (Object[] asd : resultList){
                String orderState = (String) asd[0];
                Long count = (Long) asd[1];
                System.out.println("--------상태값"+orderState+"-----------숫자--------------------"+count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
