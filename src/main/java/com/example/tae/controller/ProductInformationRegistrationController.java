package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.service.RegistrationService.ProductInfomationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductInformationRegistrationController {
    @GetMapping("ProductInformationRegistration")
    public String ProductInformationRegistration(){
        return "ProductInformationRegistration";
    }

    @GetMapping("ProductContractModal")
    public String ProductContractModal(){
        return "ProductContractModal";
    }


    @Autowired
    ProductInfomationServiceImpl productInfomationService;


    @GetMapping("/search/product")
    @ResponseBody
    public List<ProductInformationRegistration> getProductInfo() {

        return productInfomationService.getAllProductInfo();
    }

}
