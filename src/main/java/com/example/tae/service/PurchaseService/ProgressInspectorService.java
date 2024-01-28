package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;

import java.util.Date;
import java.util.List;

public interface ProgressInspectorService {
    ProgressInspectionDTO orderInsepector(OrderInspectDTO inspection);

    List<ProgressInspectionDTO> getProgressInspectorList(int planId);

    void upDateProgressInspector(int progressInspectionId, Date updateDate);

    String inspectorResult(int inspectorId, boolean progressInspectorResult);

    void deleteProgressInspector(int progressInspector);

}
