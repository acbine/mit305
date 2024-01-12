package com.example.tae.TradingServiceTest;

import com.example.tae.entity.TradingStatement.TradingStatement;
import com.example.tae.entity.TradingStatmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradingStatmentRepositoryTest {
    @Autowired
    TradingStatmentRepository tradingStatmentRepository;

    @Test
    public void repotest(){
        TradingStatement tradingStatement= TradingStatement.builder().store(9).build();
        tradingStatmentRepository.save(tradingStatement);

    }
}
