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


    // 모든 품목 검색
    @GetMapping("/search/product")
    @ResponseBody
    public List<ProductInformationRegistration> getProductInfo() {

        return productInfomationService.getAllProductInfo();
    }

    // 계약과 조인한 품목 검색
//    @GetMapping("/search/product/contract")
//    @ResponseBody
//    public List<ProductInformationRegistration> getProduct_contract() {
//
//
//
//        return null;
//    }

}
