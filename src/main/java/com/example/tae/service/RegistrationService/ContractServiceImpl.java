package com.example.tae.service.RegistrationService;

import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.DummyData.DTO.CompanyDTO;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final CompanyRepository companyRepository;

    public ContractServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<CompanyDTO> getListOfCompany(String businessNumber) {



        return null;
    }

    @Override
    public void insert_contract() {

    }

}
