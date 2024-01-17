package com.example.tae.RegistratioinTest.Repository;

import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.AssyRepository;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.repository.DummyRepository.UnitRepository;
import com.example.tae.repository.ProductForProjectRepository;
import com.example.tae.repository.ProductRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ProductInfomationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductInfomationRepositoryTest {

    @Autowired
    ProductInfomationRepository productInfomationRepository;
    @Autowired
    UnitRepository unitRepository; // 대분류
    @Autowired
    AssyRepository assyRepository; // 중뷴류
    @Autowired
    PartRepository partRepository; // 소분류

    @Autowired
    ProjectRepository projectRepository; // 제품명

    // 품목 정보 db 연동하여 insert
    @Test
    public void Test1() {

        // 소분류 id
        Part part = partRepository.findById(30).get();

        // 제품명
        Project project= projectRepository.findById("스마트폰").get();

        ProductInformationRegistration productInformationRegistration = ProductInformationRegistration.builder().
                product_name("나사").product_abbreviation('E').texture("철").width(1).length(2).height(3).weight(4).
                part(part).build();

        productInfomationRepository.save(productInformationRegistration);

    }

    @Test
    public void select1() {

        System.out.println(unitRepository.findById(1).get());
    }
}
