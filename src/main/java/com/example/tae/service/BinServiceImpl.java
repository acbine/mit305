package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.TradingStatement.TradingStatement;
import com.example.tae.repository.TradingStatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BinServiceImpl implements BinService{

    @Autowired
    TradingStatmentRepository tradingStatmentRepository;
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
        TradingStatement tradingStatement = TradingStatement.builder().store(입고수량).build();
        tradingStatmentRepository.save(tradingStatement);

    }
}
