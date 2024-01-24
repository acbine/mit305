package com.example.tae.controller;

import com.example.tae.entity.Order.dto.OrderDTO;

import com.example.tae.service.PurchaseService.OrderRegisterService;
import com.example.tae.service.PurchaseService.OrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.query.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderServiceImpl orderService;
    private final OrderRegisterService orderRegisterService;


    @GetMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("oList", orderService.getAllOrders());
        return "orderList";
    }


    @GetMapping("orderInspect")
    public String  TradingStatementModal(@RequestParam(name = "productCode")int productCode, Model model) {
        OrderDTO orderDTO = orderService.getOrderInspectData(productCode);
        log.info(orderDTO.toString());
        model.addAttribute("orderInspect", orderService.getOrderInspectData(productCode));
        return "orderInspect";
    }


    @GetMapping("orderRegister")
    public String orderRegister(Model model) {
    List<OrderDTO> oList = orderService.oListSend();
    model.addAttribute("oList", oList);
    return "orderRegister";
    }

    @PostMapping("orderRegisterData")
    public String orderRegisterData(@RequestBody OrderDTO orderDTO) {
        orderService.orderRegister(orderDTO.getProcurementPlanCode());
        return "orderRegister";
    }

    @DeleteMapping("/api/cancelOrder/{procurementplan_code}")
    @ResponseBody
    public ResponseEntity<Void> cancelOrder(@PathVariable int procurementplan_code) {
        orderRegisterService.cancelProcurementPlan(procurementplan_code);
        return ResponseEntity.noContent().build();
    }
}
