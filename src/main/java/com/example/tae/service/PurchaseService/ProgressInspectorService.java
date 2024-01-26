package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;

import java.util.List;

public interface ProgressInspectorService {
    ProgressInspectionDTO orderInsepector(OrderInspectDTO inspection);

    List<ProgressInspectionDTO> getProgressInspectorList(int planId);
}
