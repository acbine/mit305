package com.example.tae.service;

import com.example.tae.entity.TradingStatement.TradingStatement;
import com.example.tae.repository.TradingStatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BinDummyServiceImpl implements BinDummyService{

    TradingStatmentRepository tradingStatmentRepository;
    @Override
    public void binDummyinjection() {
        int[] store= {
           10,150,15
        };

        for(int aa : store) {
            TradingStatement tradingStatement= TradingStatement.builder().store(aa).build();
            tradingStatmentRepository.save(tradingStatement);
        }

    }
}
