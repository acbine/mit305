package com.example.tae.OrderServiceTests;

import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.repository.OrderRepository;
import com.example.tae.repository.ProgressInspectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryTests {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProgressInspectionRepository progressInspectionRepository;

    @Test
    public void repotest(){
        Purchase p = Purchase.builder().build();
        ProgressInspection pi = ProgressInspection.builder().purchase(p).build();
        ProgressInspection pi2 = ProgressInspection.builder().purchase(p).build();

        orderRepository.save(p);
        progressInspectionRepository.save(pi);
        progressInspectionRepository.save(pi2);

        System.out.println("발주서 코드 : " + p.getOrdercode());
        System.out.println("발주서 발행일 : " + p.getRegDate());
    }
}