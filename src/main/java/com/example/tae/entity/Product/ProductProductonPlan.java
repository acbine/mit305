package com.example.tae.entity.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 제품 생산계획에 관한 엔티티 입니다
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductProductonPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int OutputeNum;

    private Date ProjectOutputDate;

    @ManyToOne
    Product product;
}
