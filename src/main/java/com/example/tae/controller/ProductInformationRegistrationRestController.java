package com.example.tae.controller;

import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDTO;
import com.example.tae.repository.DummyRepository.AssyRepository;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.repository.DummyRepository.UnitRepository;
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
    private UnitRepository unitRepository;

    @Autowired
    private AssyRepository assyRepository;

    @Autowired
    private PartRepository partRepository;

    @PostMapping("/getUnit")
    @ResponseBody
    public List<Unit> Allunit() {

        return unitRepository.findAll();
    }

    @PostMapping("/getAssy")
    @ResponseBody
    public List<Assy> Allassy() {

        return assyRepository.findAll();
    }

    @PostMapping("/getPart")
    @ResponseBody
    public List<Part> Allpart() {

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
