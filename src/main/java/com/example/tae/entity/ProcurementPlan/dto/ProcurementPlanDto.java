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

    @Builder.Default
    List<ProjectPlan> projectPlanList = new ArrayList<>();

    @Builder.Default
    List<ContractDTO> contractDTOList = new ArrayList<>();

    private int product_amount;

    private Date order_date;

    private String order_state;


}
