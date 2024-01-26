package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.service.RegistrationService.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContractController {

    @Autowired
    ContractServiceImpl contractService;

    @Autowired
    ContractRepository contractRepository;

    @GetMapping("ContractRegistration")
    public String ContractRegistration() {
        return "ContractRegistration";
    }


    // 계약서 모달창 요청 매핑
    @GetMapping("ContractRegistrationModal")
    public String ContractRegistrationModal() {
        return "ContractRegistrationModal";
    }

    // 품목 전체 검색
    @GetMapping("/search/contract")
    @ResponseBody
    public List<Contract> getAll(){

        return contractService.getAllContracts();
    }

    // 계약 수정한 값으로 db에 수정
    @PostMapping("/edit/{contract_code}")
    @ResponseBody
    public String updateContract(@PathVariable int contract_code, @RequestBody Contract contractUpdate) {

        Contract contract = contractRepository.findById(contract_code).orElseThrow(() -> new IllegalArgumentException("Invalid entity ID: " + contract_code));

        contract.setLead_time(contractUpdate.getLead_time());
        contract.setProduct_price(contractUpdate.getProduct_price());
        contract.setPayment_method(contractUpdate.getPayment_method());

        contractRepository.save(contract);

        return "수정된 계약 코드: " + contract_code;
    }

    @GetMapping("/delete/{contract_code}")
    @ResponseBody
    public String deleteContract(@PathVariable int contract_code) {

        contractRepository.deleteById(contract_code);
        return "삭제된 계약 코드: " + contract_code;
    }

}
