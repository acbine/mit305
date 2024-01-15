package com.example.tae.OrderServiceTests;


import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryTests {
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void repotest(){
        Purchase Purchase= com.example.tae.entity.Order.Purchase.builder().build();
        orderRepository.save(Purchase);
        System.out.println("발주서 코드 : " + Purchase.getOrdercode());
        System.out.println("발주서 발행일 : " + Purchase.getRegDate());
    }


}