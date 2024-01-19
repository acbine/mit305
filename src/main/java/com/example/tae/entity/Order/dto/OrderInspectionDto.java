package com.example.tae.entity.Order.dto;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class OrderInspectionDto {
    private String product_name;
    private int product_code;
    private int supportProductAmount;

    private Date projectOutputDate;
    private int lead_time;
    private Date order_date;
    private int width;
    private int length;
    private int height;
    private int weight;
    private String texture;
    private boolean progressInspectionStatus;

    public OrderInspectionDto OrderInspectionInfo(ProductInformationRegistration productInformationRegistration, ProcurementPlan procurementPlan, ProjectPlan projectPlan, Contract contract, ProgressInspection progressInspection){
        return OrderInspectionDto.builder().product_name(productInformationRegistration.getProduct_name())
                .product_code(productInformationRegistration.getProduct_code()).supportProductAmount(procurementPlan.getSupportProductAmount())
                .projectOutputDate(projectPlan.getProjectOutputDate()).lead_time(contract.getLead_time())
                .order_date(procurementPlan.getOrder_date()).width(productInformationRegistration.getWidth())
                .length(productInformationRegistration.getLength()).height(productInformationRegistration.getHeight())
                .weight(productInformationRegistration.getWeight()).texture(productInformationRegistration.getTexture())
                .progressInspectionStatus(progressInspection.isProgressInspectionStatus()).build();
    }
}