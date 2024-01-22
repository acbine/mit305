package com.example.tae.entity.Order.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class OrderListDto {
    private String ordercode;
    private LocalDateTime regDate;

    @Getter
    private List<String> productNameList;

    private String departName;
    private boolean progressInspectionStatus;

    private static boolean checkAllTrue(List<Boolean> list) {
        for(Boolean value : list)
            if(!value)
                return false;
        return true;
    }

    public static OrderListDto OrderListInfo(Purchase purchase) {
        if (purchase == null)
            return new OrderListDto();

        boolean piStatus = false;
        if (purchase.getProgressInspections() != null) {
            List<Boolean> progressInspectionStatusList = new ArrayList<>();
            for(ProgressInspection progressInspection : purchase.getProgressInspections())
                progressInspectionStatusList.add(progressInspection.isProgressInspectionStatus());

            piStatus = checkAllTrue(progressInspectionStatusList);
        }

        List<String> productNameList = new ArrayList<>();
        for(ProcurementPlan procurementPlan : purchase.getProcurementPlan()) {
            if (procurementPlan != null && procurementPlan.getContract() != null && procurementPlan.getContract().getProductInformationRegistration() != null) {
                productNameList.add(procurementPlan.getContract().getProductInformationRegistration().getProduct_name());
            } else
                productNameList.add(null);
        }

        String departName = purchase.getProcurementPlan().isEmpty() ? null
                : purchase.getProcurementPlan().get(0).getContract().getCompany().getDepartName();

        return OrderListDto.builder().ordercode(purchase.getOrdercode()).regDate(purchase.getRegDate()).productNameList(productNameList).departName(departName)
                .progressInspectionStatus(piStatus).build();
    }
}