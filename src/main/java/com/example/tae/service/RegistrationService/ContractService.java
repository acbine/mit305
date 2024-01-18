package com.example.tae.service.RegistrationService;

import com.example.tae.entity.DummyData.DTO.CompanyDTO;

import java.util.List;

// 계약 서비스
public interface ContractService {


    List<CompanyDTO> getListOfCompany(String businessNumber);

    void insert_contract ();
}
