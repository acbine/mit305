package com.example.tae.ProductForProjectRepositoryTest;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductForProject.ProductForProjectEmbeddable;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.repository.ProductForProjectRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductForProjectRepositoryTest {
    @Autowired
    ProductForProjectRepository productForProjectRepository; //제품의 품목
    @Autowired
    ProductInformationRegistrationRepository productInfomationRepository;// 품목
    @Autowired
    ProjectRepository projectRepository;//제품

    @Test
    public void reTest(){
        Project project=projectRepository.findById("스마트폰").get();
        System.out.println("--------------------------------------"+project.getProductName());
        ProductInformationRegistration productInformationRegistration = productInfomationRepository.findById(1).get();
        System.out.println("======================="+productInformationRegistration.getProduct_name());


        ProductForProjectEmbeddable productForProjectEmbeddable = new ProductForProjectEmbeddable(productInformationRegistration, project);


        ProductForProject productForProject=ProductForProject.builder()
                .productCode(productForProjectEmbeddable.getProductCode())
                .projectID(productForProjectEmbeddable.getProjectID())
                .productCodeCount(3)
                .build();

        System.out.println(productForProject.toString());
        productForProjectRepository.save(productForProject);

    }
}
