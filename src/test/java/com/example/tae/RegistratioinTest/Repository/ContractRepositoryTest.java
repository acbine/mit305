package com.example.tae.RegistratioinTest.Repository;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.CompanyRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInfomationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ContractRepositoryTest {

    @Autowired // 회사
    CompanyRepository companyRepository;

    @Autowired // 품목 정보
    ProductInfomationRepository productInfomationRepository;

    @Autowired // 계약
    ContractRepository contractRepository;

    Date date1 = new Date(2024,01,18);
    Date date2 = new Date(2024,01,28);

    Date date3 = new Date(2024,01,15);

    @Test
    public void Test1() {

        //가존에있던 회사 정보를 불러옴
        Company company =companyRepository.findById("A403-81-80895").get();

        //품목코드 정보 넣는것
        ProductInformationRegistration productInformationRegistration = ProductInformationRegistration.builder().product_code(9).build();

        //productInfomationRepository.findById(5).get();

        Contract contract = Contract.builder().productInformationRegistration(productInfomationRepository.findById(1).get())
                .company(company).payment_method("현금지불").product_price(100).lead_time(10)
                .start_date(date1).end_date(date2).contract_date(date3).tf(false).build();



        contractRepository.save(contract);
    }
}
