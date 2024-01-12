package com.example.tae.repository.DummyRepository;

import com.example.tae.entity.DummyData.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
