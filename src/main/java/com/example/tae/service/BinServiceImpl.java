package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.repository.ReceivingProcessingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BinServiceImpl implements BinService{

    ReceivingProcessingRepository receivingProcessingRepository;
    @Override
    public List<ProcurementPlan> ProcumentPlanList() {
        return null;
    }

    @Override
    public List<ProcurementPlan> ProcumentPlanSearchList(String 보내는칸, String 검색종류, String 입고처리상태) {
        return null;
    }

    @Override
    public void ReceivingProcessStore( int 입고수량) {
        ReceivingProcessing receivingProcessing = ReceivingProcessing.builder().store(입고수량).build();
        receivingProcessingRepository.save(receivingProcessing);

    }
}
