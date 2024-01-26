package com.example.tae.controller;


import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;
import com.example.tae.service.PurchaseService.ProgressInspectorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ProgressInspectorController {
    private ProgressInspectorServiceImpl progressInspectorService;

    @PostMapping("orderInspect")
    @ResponseBody
    public ResponseEntity<?> orderInspect(@RequestBody OrderInspectDTO inspect) {
        ProgressInspectionDTO progressInspection = progressInspectorService.orderInsepector(inspect);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("progressInspection",progressInspection));
    }

    @GetMapping("inspectorData")
    @ResponseBody
    public ResponseEntity<?> getProgressInspectorList(@RequestParam("planCode") int planCode) {
        List<ProgressInspectionDTO> progressInspectionList = progressInspectorService.getProgressInspectorList(planCode);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("progressInspectionList",progressInspectionList));
    }

    @DeleteMapping("inspectorData")
    @ResponseBody
    public void deleteProgressInspector() {

    }
}
