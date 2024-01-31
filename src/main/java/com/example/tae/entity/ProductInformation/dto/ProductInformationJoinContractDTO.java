package com.example.tae.entity.ProductInformation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductInformationJoinContractDTO {

    private int product_code;

    private String product_name;

    private String product_abbreviation;

    private String texture;

    private int width;
    private int length;
    private int height;
    private int weight;

    private String image_name;

    @JsonFormat(shape = JsonFormat.Shape.STRING) // 계약 코드가 null 이어도 가져와야 함
    private Long contract_code;

    private String part; // part의 id
    private String assy;
    private String unit;


}
