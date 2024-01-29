package com.example.tae.repository;

import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductForProject.ProductForProjectEmbeddable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductForProjectRepository extends JpaRepository <ProductForProject, ProductForProjectEmbeddable> {

    @Query("select pf from ProductForProject    pf" +
        " where pf.productCode.product_code = :productId ")
    Optional<ProductForProject> findByProdcutId(@Param("productId") int productId);
}
