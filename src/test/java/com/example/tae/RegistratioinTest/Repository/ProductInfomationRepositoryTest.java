package com.example.tae.RegistratioinTest.Repository;

import com.example.tae.entity.DummyData.Classification.Assy;
import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Classification.Unit;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.DummyRepository.AssyRepository;
import com.example.tae.repository.DummyRepository.PartRepository;
import com.example.tae.repository.DummyRepository.UnitRepository;
import com.example.tae.repository.ProductRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ProductInfomationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        Unit unit = Unit.builder().unit("대분류").build();
        unitRepository.save(unit);

        Assy assy = Assy.builder().unit(unit).assy("중분류").build();
        assyRepository.save(assy);

        Part part = Part.builder().assy(assy).part("소분류").build();
        partRepository.save(part);




        ProductInformationRegistration productInformationRegistration = ProductInformationRegistration.builder().
                product_name("나사").product_abbreviation('E').texture("철").width(1).length(2).height(3).weight(4).
                part(part).build();

        productInfomationRepository.save(productInformationRegistration);

    }

    @Test
    public void select1() {


    }
}
