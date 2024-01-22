package com.example.tae.entity.ProductInformation.dto;

import com.example.tae.entity.DummyData.DTO.PartDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformationRegistrationDTO {

    private int product_code;

    private Long id; // part의 id

    private String product_name;

    private char product_abbreviation;

    private String texture;

    private int width;
    private int length;
    private int height;
    private int weight;

    private String product_imageURL;
}
