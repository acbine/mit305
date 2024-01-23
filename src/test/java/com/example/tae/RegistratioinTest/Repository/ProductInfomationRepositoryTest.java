package com.example.tae.RegistratioinTest.Repository;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.AssyRepository;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.repository.DummyRepository.UnitRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductInfomationRepositoryTest {

    @Autowired
    ProductInformationRegistrationRepository productInfomationRepository;
    @Autowired
    UnitRepository unitRepository; // 대분류
    @Autowired
    AssyRepository assyRepository; // 중뷴류
    @Autowired
    PartRepository partRepository; // 소분류

    @Autowired
    ProjectRepository projectRepository; // 제품명

    @Autowired
    ContractRepository contractRepository;

    // 품목 정보 db 연동하여 insert
    @Test
    public void Test1() {

        // 소분류 id
//        Part part = partRepository.findById(1).get();

        // 제품명
//        Project project= projectRepository.findById("스마트폰").get();
//
//        ProductInformationRegistration productInformationRegistration = ProductInformationRegistration.builder().
//                product_name("액정").product_abbreviation('E').texture("철").width(1).length(2).height(3).weight(4).
//                part(part).build();
//
//        productInfomationRepository.save(productInformationRegistration);

    }

    // 전체 대분류 리스트
    @Test
    public void UnitList() {

        List<Unit> unitlist = unitRepository.findAll();

        System.out.print("대분류 리스트 : " + unitlist);

    }

    @Test
    public void AssyList() {

        List<Assy> assyList = assyRepository.findAll();

        System.out.print("중분류 리스트 : " + assyList);
    }

    @Test
    public void PartList() {

        List<Part> partList = partRepository.findAll();

        System.out.println("소분류 리스트 : " + partList);
    }

    @Test
    public void ProductList() {

        List<ProductInformationRegistration> productInformationRegistrationList = productInfomationRepository.findAll();

        System.out.println("품목 리스트: " + productInformationRegistrationList);

    }

    @Test
    public void Product_ContractState() {

        Optional<Contract> contract = contractRepository.findById(4);

        Contract contractInfo = contract.get();

        System.out.println("2번 품목의 계약 정보 : " + contractInfo);
    }


}
