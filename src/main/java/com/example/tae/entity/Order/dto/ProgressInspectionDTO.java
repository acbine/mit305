package com.example.tae.entity.Order.dto;

import com.example.tae.entity.Order.ProgressInspection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProgressInspectionDTO {
    private String productName;
    private int progressInspectionId;
    private Date progressInspectonDate;
    private LocalDateTime orderDate;
    private boolean progressInspectorResult;
    private String orderState;
    private String note;

    public static ProgressInspectionDTO makeDt(ProgressInspection progressInspection, String productName, LocalDateTime orderDate,String orderState) {
        return ProgressInspectionDTO.builder()
                .orderState(orderState)
                .productName(productName)
                .progressInspectonDate(progressInspection.getProgressInspectionPlan())
                .progressInspectionId(progressInspection.getProgressInspectionNum())
                .progressInspectorResult(progressInspection.isProgressInspectionStatus())
                .orderDate(orderDate)
                .note(progressInspection.getNote())
                .build();
    }

}
