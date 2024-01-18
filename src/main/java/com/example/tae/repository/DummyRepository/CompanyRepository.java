package com.example.tae.repository.DummyRepository;

import com.example.tae.entity.DummyData.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query("select businessNumber from Company")
    List<Company> getListOfCompany();
}
