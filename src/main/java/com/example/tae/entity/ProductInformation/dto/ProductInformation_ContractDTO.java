package com.example.tae.entity.ProductInformation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductInformation_ContractDTO {

    private int product_code;

    private Long id; // part의 id

    private String product_name;

    private char product_abbreviation;

    private String texture;

    private int width;
    private int length;
    private int height;
    private int weight;

//    private String product_imageURL;

    @JsonFormat(shape = JsonFormat.Shape.STRING) // 계약 코드가 null 이어도 가져와야 함
    private int contract_code;

}
