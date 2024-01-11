package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReleaseController {
    @GetMapping("existence")
    public String existence() {
        return "existence";
    }



}
