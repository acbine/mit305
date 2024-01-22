package com.example.tae.RegistratioinTest.Repository;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ContractRepositoryTest {

    @Autowired // 회사
    CompanyRepository companyRepository;

    @Autowired // 품목 정보
    ProductInformationRegistrationRepository productInfomationRepository;

    @Autowired // 계약
    ContractRepository contractRepository;

    Date date1 = new Date(2024,01,18);
    Date date2 = new Date(2024,01,28);

    Date date3 = new Date(2024,01,15);

    @Test
    public void Test1() {

        //가존에있던 회사 정보를 불러옴
        Company company =companyRepository.findById("A403-81-80895").get();

//        Contract contract = Contract.builder().productInformationRegistration(productInfomationRepository.findById(1).get());
        //품목코드 정보 넣는것
        ProductInformationRegistration productInformationRegistration = productInfomationRepository.findById(2).get();

        Contract contract1 = Contract.builder().productInformationRegistration(productInformationRegistration)
                .company(company).payment_method("현금지불").product_price(100).lead_time(10)
                .start_date(date1).end_date(date2).contract_date(date3).build();

        contractRepository.save(contract1);
    }

    @Test
    public void ProductInfoTest() {

        Optional<ProductInformationRegistration> productList = productInfomationRepository.findById(1);

//        ProductInformationRegistration info = productList.get().getProduct_name();
        String info = productList.get().getProduct_name();

        System.out.print("품목 정보 : " + info);
    }

    @Test
    public void CompanyTest() {

        Optional<Company> companyList = companyRepository.findById("A403-81-80895");

        Company company = companyList.get();


        System.out.print("사업자 정보: " + company);

    }

    @Test
    public void delete() {


    }

}
