package com.example.tae.TradingServiceTest;

import com.example.tae.entity.TradingStatement.TradingStatement;
import com.example.tae.repository.TradingStatmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TradingStatmentRepositoryTest {
    @Autowired
    TradingStatmentRepository tradingStatmentRepository;

    @Test
    public void repotest(){
        //TradingStatement tradingStatement= TradingStatement.builder().store(9).build();
        //tradingStatmentRepository.save(tradingStatement);


        System.out.println("------------------");

        List<TradingStatement> tradingStatementList =tradingStatmentRepository.findAll();
        tradingStatementList.forEach(x-> System.out.println(x.getStore()));
        System.out.println("---------------------------------");
        System.out.println(tradingStatmentRepository.findById(6).get().getStore());


    }
}
