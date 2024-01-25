package com.example.tae.controller;

import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;

import com.example.tae.service.PurchaseService.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;



    @GetMapping("/orderList")
    public String orderList(Model model) {
        return "orderList";
    }

    @GetMapping("/orderListPopup")
    public String orderListPopup(@RequestParam(name = "ordercode")String ordercode, Model model) {
        return "orderListPopup";
    }

    @GetMapping("/orderInspect/{ordercode}")
    public String orderInspect(@PathVariable String ordercode, Model model){
        //model.addAttribute("orderDetails", orderService.getOrderInspection(ordercode));
        return "orderInspect";
    }

    @GetMapping("/orderInspect1_1")
    public String orderIns1(){
        return "orderInspect1_1";
    }

    @GetMapping("/orderInspect1_2")
    public String orderIns2(){
        return "orderInspect1_2";
    }

    @GetMapping("/orderInspect4")
    public String orderIns4(){
        return "orderInspect4";
    }

//    @RequestParam("departName")String departName
    @GetMapping("orderRegister")
    public String orderRegister(Model model) {
        return "orderRegister";
    }
}
