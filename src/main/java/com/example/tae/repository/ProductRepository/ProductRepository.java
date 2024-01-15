package com.example.tae.repository.ProductRepository;

import com.example.tae.entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
