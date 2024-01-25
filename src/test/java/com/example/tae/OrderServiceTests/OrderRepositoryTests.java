package com.example.tae.OrderServiceTests;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.repository.PurchaseRepository.OrderRepository;
import com.example.tae.repository.ProgressInspectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderRepositoryTests {
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    ProgressInspectionRepository progressInspectionRepository;
//
//    @Test
//    public void repotest(){
//        Purchase p = Purchase.builder().build();
//        ProgressInspection pi = ProgressInspection.builder().purchase(p).build();
//        ProgressInspection pi2 = ProgressInspection.builder().purchase(p).build();
//
//        orderRepository.save(p);
//        progressInspectionRepository.save(pi);
//        progressInspectionRepository.save(pi2);
//
//        System.out.println("발주서 코드 : " + p.getOrderCode());
//        System.out.println("발주서 발행일 : " + p.getRegDate());
//    }
//
//    @Test
//    public void repotest2() {
//        List<Purchase> allOrders = orderRepository.findAll();   // OrderRepository에서 모든 Order 요소 가져오기
//        Purchase selectedOrder = allOrders.get(2);
//        ProgressInspection progressInspection = ProgressInspection.builder().purchase(selectedOrder).build();
//        progressInspectionRepository.save(progressInspection);
//        System.out.println("Saved ProgressInspection: " + progressInspection.getPurchase());
//    }
//    @Test
//    public void repotest3() {
//        Purchase purchase = orderRepository.findById("100118d0-4889-4df1-b3be-be40e10f897f").orElse(null);
//        ProgressInspection pi = ProgressInspection.builder().purchase(purchase).build();
//        progressInspectionRepository.save(pi);
//        System.out.println("발주서 코드 : " + pi.getProgressInspectionNum());
//        System.out.println("발주서 발행일 : " + pi.getPurchase().getRegDate());
//    }


}
