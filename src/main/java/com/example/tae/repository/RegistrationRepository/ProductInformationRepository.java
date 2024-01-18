package com.example.tae.repository.RegistrationRepository;


import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInformationRepository extends JpaRepository <ProductInformationRegistration, Integer> {
    @Query("select p from ProductInformationRegistration p " +
        "where p.product_name = :proName")
    List<ProductInformationRegistration> findByProductInformationName(String proName);
    @Query("select p from  ProductInformationRegistration p " +
        "where p.product_code = :proCode")
    List<ProductInformationRegistration> findByProductInformationCode(String proCode);

}
