package com.example.tae.entity.ProductForProject;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductForProjectEmbeddable implements Serializable  {


    private ProductInformationRegistration productCode; //품목코드


    private Project projectID; //제품명
}

