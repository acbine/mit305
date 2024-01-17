package com.example.tae.entity.ProductForProject;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductForProjectEmbeddable implements Serializable  {


    private ProductInformationRegistration productCode; //품목코드


    private Project projectID; //제품명
}
