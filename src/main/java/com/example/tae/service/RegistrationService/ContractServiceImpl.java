package com.example.tae.service.RegistrationService;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    private final CompanyRepository companyRepository;

    public ContractServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

}
