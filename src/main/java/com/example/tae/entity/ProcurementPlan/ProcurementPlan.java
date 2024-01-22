package com.example.tae.entity.ProcurementPlan;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProductForProject.ProductForProject;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.SimpleTimeZone;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class ProcurementPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int procurementplan_code; // 조달 계획 코드

    // 생산 계획 키값
    @ManyToOne(fetch = FetchType.LAZY)
    ProjectPlan projectPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    Contract contract; // 계약(계약코드)

    @ManyToOne(fetch = FetchType.LAZY) //발주서 코드
    Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    ProductForProject productForProject;//제품에대한 품목수량  ID:

    private int SupportProductAmount; //조달수량

    private Date order_date; //발주일
    
    private String order_state; //품목에 대한 발주상태
}
