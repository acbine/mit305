package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
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
    public String orderList() {
        return "orderList";
    }

    @GetMapping("orderRegister")
    public String orderRegister() {return "orderRegister";}

}
