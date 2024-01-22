package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.service.RegistrationService.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContractController {

    @Autowired
    ContractServiceImpl contractService;

    @GetMapping("ContractRegistration")
    public String ContractRegistration() {
        return "ContractRegistration";
    }


    // 계약서 모달창 요청 매핑
    @GetMapping("ContractRegistrationModal")
    public String ContractRegistrationModal() {
        return "ContractRegistrationModal";
    }

    @GetMapping("/search/contract")
    @ResponseBody
    public List<Contract> getAll(){

        return contractService.getAllContracts();
    }



}
