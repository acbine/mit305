package com.example.tae.repository;

import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductForProject.ProductForProjectEmbeddable;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductForProjectRepository extends JpaRepository <ProductForProject,ProductForProjectEmbeddable> {
}
