package com.example.tae.repository.RegistrationRepository;


import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInformationRepository extends JpaRepository <ProductInformationRegistration, Integer> {
}
