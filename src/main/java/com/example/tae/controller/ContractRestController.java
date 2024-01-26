package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import com.example.tae.service.RegistrationService.ContractServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ContractRestController {

    @Autowired
    ProductInformationRegistrationRepository productInformationRegistrationRepository;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/search/pro") // 품목 코드 검색
    @ResponseBody
    public int searchProductId(@RequestParam(name = "name") String name) {

        System.out.println(name);

        List<ProductInformationRegistration> NameSearch
                = productInformationRegistrationRepository.findByProductInformationName(name);

        if(!NameSearch.isEmpty()) {

            return  NameSearch.get(0).getProduct_code();

        } else {
            return  0;
        }
    }

    @PostMapping("/search/com") // 사업자 번호 검색
    @ResponseBody
    public String searchCompanyId(@RequestParam(name = "comName") String comName) {

        List<Company> NameSearch2 = companyRepository.findBydepartName(comName);

//        System.out.println("리스트의 0번째 : " + NameSearch2.get(0));

        if(!NameSearch2.isEmpty()) {

            return  NameSearch2.get(0).getBusinessNumber();

        } else {
            return  null;
        }
    }

    @Autowired
    ContractRepository contractRepository;

    @PostMapping("/register") // 입력한 데이터를 db에 저장
    public void ContractRegister(@RequestBody List<ContractDTO>  contractDTOList) {

        for(ContractDTO data : contractDTOList) {

            ProductInformationRegistration productInformationRegistration
                    = productInformationRegistrationRepository.findById(data.getProduct_code()).orElse(null);

            Company company = companyRepository.findById(data.getBusinessNumber()).orElse(null);

            if(productInformationRegistration != null && company != null) {
                Contract contract = new Contract();

                contract.setProductInformationRegistration(productInformationRegistration); // 품목 코드 외래키
                contract.setLead_time(data.getLead_time()); // 리트 타임
                contract.setCompany(company); // 사업자 번호
                contract.setProduct_price(data.getProduct_price()); // 단가
                contract.setPayment_method(data.getPayment_method()); // 지불 방법

                contractRepository.save(contract);
            }

        }
    }

    @Autowired
    ContractServiceImpl contractService;



}
