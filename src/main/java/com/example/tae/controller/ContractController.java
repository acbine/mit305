package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractController {

    @GetMapping("ContractRegistration")
    public String ContractRegistration() {
        return "ContractRegistration";
    }


    // 계약서 모달창 요청 매핑
    @GetMapping("ContractRegistrationModal")
    public String ContractRegistrationModal() {
        return "ContractRegistrationModal";
    }
}
