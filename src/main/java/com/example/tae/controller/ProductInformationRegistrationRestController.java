package com.example.tae.controller;

import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.service.RegistrationService.ProductInfomationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductInformationRegistrationRestController {

    @Autowired
    private ProductInfomationServiceImpl productInfomationService;


//    public String insertProduct(return "";);


    @Autowired
    private PartRepository partRepository;

    @PostMapping("/getPart")
    @ResponseBody
    public List<Part> Allunit() {

        return partRepository.findAll();
    }

    @PostMapping("/saveData")
    @ResponseBody
    public String saveData(@ModelAttribute ProductInformationRegistrationDTO productInformationRegistrationDTO) {

        try {


            productInfomationService.saveProductInfo(productInformationRegistrationDTO);

            return  "품목 정보 등록 완료";
        } catch (IOException e) {
            return "품목 정보 등록 오류: " +  e.getMessage();
        }
    }
}
