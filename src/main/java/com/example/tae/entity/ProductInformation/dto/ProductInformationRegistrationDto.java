package com.example.tae.entity.ProductInformation.dto;

import com.example.tae.entity.DummyData.Classification.Part;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformationRegistrationDto {

    private int product_code;

    @Builder.Default
    List<Part> partList = new ArrayList<>();

    private String product_name;

    private char product_abbreviation;

    private String texture;

    private int width;
    private int length;
    private int height;
    private int weight;

    public ProductInformationRegistration productInformationRegistration () {

        return ProductInformationRegistration.builder()
                .product_code(product_code)
                .part((Part) partList)
                .product_name(product_name)
                .product_abbreviation(product_abbreviation)
                .texture(texture)
                .width(width)
                .length(length)
                .height(height)
                .weight(weight)
                .build();
    }

}
