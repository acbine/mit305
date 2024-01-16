package com.example.tae.TradingServiceTest;

import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.repository.ReceivingProcessingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TradingStatmentRepositoryTest {

    @Autowired
    ReceivingProcessingRepository receivingProcessingRepository;

    @Test
    public void repotest(){
        //ReceivingProcessing tradingStatement= TradingStatement.builder().store(9).build();
        //tradingStatmentRepository.save(tradingStatement);


        System.out.println("------------------");

        List<ReceivingProcessing> tradingStatementList =receivingProcessingRepository.findAll();
        tradingStatementList.forEach(x-> System.out.println(x.getStore()));
        System.out.println("---------------------------------");
        System.out.println(receivingProcessingRepository.findById(6).get().getStore());


    }
}
