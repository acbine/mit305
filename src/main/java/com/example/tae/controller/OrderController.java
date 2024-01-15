package com.example.tae.controller;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("orderInspect1_1")
    public String orderInspect1_1() {
        return "orderInspect1_1";
    }

    @GetMapping("orderInspect1_2")
    public String orderInspect1_2() {
        return "orderInspect1_2";
    }

    @GetMapping("orderInspect4")
    public String orderInspect4() {
        return "orderInspect4";
    }

    @GetMapping("orderList")
    public String orderList(Model model) {
        List<Purchase> purchaseList = orderRepository.findAll();
        model.addAttribute("pList", purchaseList);

        return "orderList";
    }

    @GetMapping("orderRegister")
    public String orderRegister() {return "orderRegister";}

    @GetMapping("orderListPopup")
    public String orderListPopup(@RequestParam("ordercode") String ordercode, Model model) {
        //Purchase purchase =
        return "orderListPopup";
    }
}
