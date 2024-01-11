package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {
    @GetMapping("stockDelivery")
    public String stockDelivery() {
        return "stockDelivery";
    }

}
