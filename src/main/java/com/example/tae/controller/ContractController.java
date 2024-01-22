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

    @GetMapping("/search/contract")
    @ResponseBody
    public List<Contract> getAll(){

        return contractService.getAllContracts();
    }

    @PostMapping("/edit/{contract_code}")
    @ResponseBody
    public String updateContract(@PathVariable int contract_code, @RequestBody Contract contractUpdate) {

        Contract contract = contractRepository.findById(contract_code).orElseThrow(() -> new IllegalArgumentException("Invalid entity ID: " + contract_code));

        contract.setLead_time(contractUpdate.getLead_time());
        contract.setStart_date(contractUpdate.getStart_date());
        contract.setEnd_date(contractUpdate.getEnd_date());
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
