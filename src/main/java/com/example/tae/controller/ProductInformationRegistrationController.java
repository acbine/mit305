package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductForProject.ProductForProjectEmbeddable;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationJoinContractDTO;
import com.example.tae.repository.ProductForProjectRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import com.example.tae.service.RegistrationService.ProductInfomationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductInformationRegistrationController {
    @GetMapping("ProductInformationRegistration")
    public String ProductInformationRegistration(){
        return "ProductInformationRegistration";
    }

    @GetMapping("ProductContractModal")
    public String ProductContractModal(){
        return "ProductContractModal";
    }


    @Autowired
    ProductInfomationServiceImpl productInfomationService;

    @Autowired
    ProductInformationRegistrationRepository productInformationRegistrationRepository;

    @Autowired
    ProductForProjectRepository productForProjectRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/search/test")
    @ResponseBody
    public List<ProductInformationJoinContractDTO> getAllInfo() {

        List<ProductInformationJoinContractDTO> reuslt = productInfomationService.getProductJoin();


        return reuslt;
    }

    // 품목 정보 수정
    @PostMapping("/info_edit/{product_code}")
    @ResponseBody
    public String updateProduct(@PathVariable(value = "product_code") int product_code, @RequestBody ProductInformationRegistration info_update) {

        ProductInformationRegistration productInformationRegistration = productInformationRegistrationRepository.findById(product_code).orElse(null);

        productInformationRegistration.setProduct_abbreviation(info_update.getProduct_abbreviation());
        productInformationRegistration.setTexture(info_update.getTexture());
        productInformationRegistration.setWidth(info_update.getWidth());
        productInformationRegistration.setLength(info_update.getLength());
        productInformationRegistration.setHeight(info_update.getHeight());
        productInformationRegistration.setWeight(info_update.getWeight());

        productInformationRegistrationRepository.save(productInformationRegistration);

        return " 수정된 품목 코드 : " + product_code;
    }

    // 품목 정보 삭제
    @GetMapping("/delete/product/{product_code}")
    @ResponseBody
    public String deleteProduct(@PathVariable(value = "product_code") int product_code) {


        ProductInformationRegistration p_code = productInformationRegistrationRepository.findById(product_code).orElse(null);

        Project project = projectRepository.findById("스마트폰").orElse(null);

        // 복합키 삭제
        ProductForProjectEmbeddable productForProjectEmbeddable = new ProductForProjectEmbeddable();
        productForProjectEmbeddable.setProductCode(p_code);
        productForProjectEmbeddable.setProjectID(project);


        productForProjectRepository.deleteById(productForProjectEmbeddable);


        productInformationRegistrationRepository.deleteById(product_code);

        return "삭제된 품목 코드: " + product_code;
    }

}
