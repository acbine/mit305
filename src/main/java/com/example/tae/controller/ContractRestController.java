package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRepository;
import com.example.tae.service.RegistrationService.ContractService;
import com.example.tae.service.RegistrationService.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ContractRestController {

    @Autowired
    ProductInformationRepository productInformationRepository;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/search/pro") // 품목 코드 검색
    @ResponseBody
    public int searchProductId(@RequestParam String name) {

        List<ProductInformationRegistration> NameSearch
                = productInformationRepository.findByProductInformationName(name);

//        System.out.println(NameSearch.get(0));

        if(!NameSearch.isEmpty()) {

            return  NameSearch.get(0).getProduct_code();

        } else {
            return  0;
        }
    }

    @PostMapping("/search/com") // 사업자 번호 검색
    @ResponseBody
    public String searchCompanyId(@RequestParam String name) {

        List<Company> NameSearch2 = companyRepository.findBydepartName(name);

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
                    = productInformationRepository.findById(data.getProduct_code()).orElse(null);

            Company company = companyRepository.findById(data.getBusinessNumber()).orElse(null);

//            System.out.println("입력 받은 품목 코드 : "+ productInformationRegistration);
//            System.out.println("입력 받은 사업자 번호 : "+ company);

            if(productInformationRegistration != null && company != null) {
                Contract contract = new Contract();

                contract.setProductInformationRegistration(productInformationRegistration); // 품목 코드 외래키
                contract.setLead_time(data.getLead_time()); // 리트 타임
                contract.setCompany(company); // 사업자 번호
                contract.setProduct_price(data.getProduct_price()); // 단가
                contract.setPayment_method(data.getPayment_method()); // 지불 방법
                contract.setStart_date(data.getStart_date()); // 계약 시작일
                contract.setEnd_date(data.getEnd_date()); // 계약 종료일
                contract.setTf(data.isTf()); // 계약서 등록 여부

                contractRepository.save(contract);
            }

        }
    }

    @Autowired
    ContractServiceImpl contractService;



}
