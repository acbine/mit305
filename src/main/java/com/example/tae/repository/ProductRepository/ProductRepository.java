package com.example.tae.repository.ProductRepository;

import com.example.tae.entity.DummyData.Product.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Project,String> {
}
