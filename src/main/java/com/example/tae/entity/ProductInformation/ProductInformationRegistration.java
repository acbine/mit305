package com.example.tae.entity.ProductInformation;

import com.example.tae.entity.DummyData.Classification.Part;
import com.example.tae.entity.DummyData.Product.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInformationRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_code;

    @ManyToOne(fetch = FetchType.LAZY) // 소분류
    Part part;

    @ManyToOne // 제품명
    Project project;

    private String product_name;

    private char product_abbreviation;

    private String texture;

    private int width;
    private int length;
    private int height;
    private int weight;

//    private String product_image;
}
