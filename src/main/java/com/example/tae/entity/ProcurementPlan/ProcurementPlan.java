package com.example.tae.entity.ProcurementPlan;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProductForProject.ProductForProject;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Setter
public class ProcurementPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int procurementplan_code; // 조달 계획 코드

    // 생산 계획 키값
    @ManyToOne
    ProjectPlan projectPlan;

    @ManyToOne
    Contract contract; // 계약(계약코드)

    @ManyToOne //발주서 코드
    @JsonIgnore // 발주서 코드가 안 생긴 조달 계획도 불러옴
    Purchase purchase;

    @ManyToOne
    Project project; // 제품명

    private int SupportProductAmount; //조달수량

    @Temporal(TemporalType.DATE)
    private Date order_date; //발주일
    
    private String order_state; //품목에 대한 발주상태

}
