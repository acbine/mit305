package com.example.tae.controller;

import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import com.example.tae.service.RegistrationService.ProductInfomationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductInformationRegistrationRestController {

    private ProductInfomationServiceImpl productInfomationService;

    @PostMapping("/insertInfo")
    public void InsertInfo(@RequestBody ProductInformationRegistrationDTO productInformationRegistrationDTO) {

        productInfomationService.insert_info(productInformationRegistrationDTO);
    }
//    public String insertProduct(return "";);

}
