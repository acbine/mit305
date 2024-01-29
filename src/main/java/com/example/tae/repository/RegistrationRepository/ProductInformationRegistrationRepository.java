package com.example.tae.repository.RegistrationRepository;


import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInformationRegistrationRepository extends JpaRepository <ProductInformationRegistration, Integer> {
    @Query("select p from ProductInformationRegistration p "+
            "where p.product_name = :proName")
    List<ProductInformationRegistration> findByProductInformationName(@Param("proName") String proName);
    @Query("select p from ProductInformationRegistration p " +
        "where p.product_code = :proCode")
    List<ProductInformationRegistration> findByProductInformationCode(@Param("proCode") String proCode);


    @Query(value = "SELECT p.product_code, p.product_name, p.product_abbreviation, p.texture, p.width, p.length, p.height, p.weight, p.image_name, IFNULL(c.contract_code, 0) AS contract_code, part.part, assy.assy, unit.unit " +
            "FROM product_information_registration p " +
            "LEFT OUTER JOIN contract c ON c.product_information_registration_product_code = p.product_code, " +
            "part, assy, unit " +
            "WHERE part.id = p.part_id " +
            "AND part.assy_id = assy.assy_id " +
            "AND assy.unit_id = unit.unit_id", nativeQuery = true)
    List<Object[]> findProductWithContract();

}
