package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductInformationRegistrationController {
    @GetMapping("ProductInformationRegistration")
    public String ProductInformationRegistration(){
        return "ProductInformationRegistration";
    }


}
