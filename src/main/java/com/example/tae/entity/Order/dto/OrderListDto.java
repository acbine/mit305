package com.example.tae.entity.Order.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
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

    public static OrderListDto OrderListInfo(Purchase purchase) {
        if (purchase == null) {
            return new OrderListDto();
        }

        List<Boolean> progressInspectionStatusList = purchase.getProgressInspections() != null
                ? purchase.getProgressInspections().stream().map(ProgressInspection::isProgressInspectionStatus)
                .toList() : Collections.singletonList(false);

        List<String> productNameList = purchase.getProcurementPlan().stream().map(procurementPlan -> procurementPlan != null
                        ? procurementPlan.getContract().getProductInformationRegistration().getProduct_name() : null).toList();

        String departName = purchase.getProcurementPlan().isEmpty() ? null
                : purchase.getProcurementPlan().get(0).getContract().getCompany().getDepartName();

        return OrderListDto.builder().ordercode(purchase.getOrdercode()).regDate(purchase.getRegDate()).productNameList(productNameList).departName(departName)
                .progressInspectionStatus(progressInspectionStatusList.stream().allMatch(Boolean::booleanValue)).build();
    }
}