package com.example.tae.entity.ProductForProject;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(ProductForProjectEmbeddable.class)
public class ProductForProject {
    @Id
    @ManyToOne(cascade=CascadeType.MERGE)
    private ProductInformationRegistration productCode; //품목코드

    @Id
    @ManyToOne(cascade=CascadeType.MERGE)
    private Project projectID; //제품명

    private int productCodeCount; //품목수량
}
