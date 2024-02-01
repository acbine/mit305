package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.Contract.ContractPage;
import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import com.example.tae.repository.RegistrationRepository.ContractPageRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
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

    @Autowired
    ContractPageRepository contractPageRepository;

    @Autowired
    ContractRepository contractRepository;

    @PostMapping("/search/pro") // 품목 코드 검색
    public int searchProductId(@RequestParam(name = "name") String name) {

        System.out.println(name);

        List<Contract> contractList = contractRepository.findAll();
        contractList.forEach(contract ->
                {if(contract.getProductInformationRegistration().getProduct_name().equals(name)) {
                        throw new IllegalArgumentException("해당 품목에 대한 계약이 존재하고 있습니다.");
                    }});

        List<ProductInformationRegistration> NameSearch
                = productInformationRegistrationRepository.findByProductInformationName(name);

        if(!NameSearch.isEmpty()) {

            return  NameSearch.get(0).getProduct_code();

        } else {
            return  0;
        }
    }

    @PostMapping("/search/com") // 사업자 번호 검색
    public String searchCompanyId(@RequestParam(name = "comName") String comName) {

        List<Company> NameSearch2 = companyRepository.findBydepartName(comName);
        NameSearch2.forEach(x-> System.out.println(x));
//        System.out.println("리스트의 0번째 : " + NameSearch2.get(0));

        if(!NameSearch2.isEmpty()) {

            return  NameSearch2.get(0).getBusinessNumber();

        } else {
            return  null;
        }
    }

    @PostMapping("/register") // 입력한 데이터를 db에 저장
    public void ContractRegister(@RequestBody List<ContractDTO>  contractDTOList) {


        for(ContractDTO data : contractDTOList) {
            log.info(data.toString());
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

    @PostMapping("/registration_page/{contract_code}")
    public String RegisterPage(@PathVariable(value = "contract_code") int contract_code) {

        ContractPage contractPage = new ContractPage();

        Contract contract = contractRepository.findById(contract_code).orElse(null);
        log.info("찾은 계약 코드 값: " + contract);

        contractPage.setContract(contract);

        contractPageRepository.save(contractPage);

        return "계약서에 등록된 계약 코드: " + contract_code;
    }

    // 사업자 번호에 맞는 계약서에 등록된 계약 코드 검색
    @PostMapping("/search/codes/{businessNumber}")
    public List<ContractPage> getContract_code(@PathVariable(value = "businessNumber") String businessNumber) {

        System.out.println("/search/codes 역기서 받은 사업자 번호"+businessNumber);
        List<ContractPage> contractPageList = contractPageRepository.findContractCodes_of_businessNumber(businessNumber);


        contractPageList.forEach(x -> System.out.println(x));


        return contractPageList;

    }

    // 계약코드에 맞는 계약서 작성일 업데이트



}
