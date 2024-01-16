package com.example.tae.entity.ProcurementPlan;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.Product.Product;
import com.example.tae.entity.Product.ProductProductonPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.SimpleTimeZone;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProcurementPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plan_code; // 조달 계획 코드

    // 생산 계획 키값
    @ManyToOne(fetch = FetchType.LAZY)
    ProductProductonPlan productProductonPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    Contract contract; // 계약

    private int product_amount;

    private Date order_date;

    // 품목 발주 상태?

    private String order_state;
}
