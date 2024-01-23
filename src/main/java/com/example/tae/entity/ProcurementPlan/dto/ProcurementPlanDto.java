package com.example.tae.entity.ProcurementPlan.dto;

import com.example.tae.entity.Contract.dto.ContractDTO;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementPlanDto {

    private int procurementplan_code;

    private int id; // 생산 계획

    private int contract_code; // 계약

    private int product_code; // 품목 수량의 품목 코드

    private String ordercode; // 발주서 코드

    private int SupportProductAmount; // 조달 수량

    private Date order_date; // 발주일

    private String order_state; // 상태


}
