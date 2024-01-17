package com.example.tae.entity.dto;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExistenceDTO {
    private int inventory;

    public int existence(int release, ReceivingProcessing receivingProcessing) {
            return receivingProcessing.getStore() - release;
    }
}
