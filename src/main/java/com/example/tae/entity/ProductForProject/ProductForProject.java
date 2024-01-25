package com.example.tae.entity.ProductForProject;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(ProductForProjectEmbeddable.class)
@ToString
public class ProductForProject {
    @Id
    @ManyToOne(cascade=CascadeType.MERGE)
    private ProductInformationRegistration productCode; //품목코드

    @Id
    @ManyToOne(cascade=CascadeType.MERGE)
    private Project projectID; //제품명

    private int productCodeCount; //품목수량
}
