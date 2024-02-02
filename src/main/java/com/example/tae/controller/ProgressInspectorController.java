package com.example.tae.controller;


import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;
import com.example.tae.service.PurchaseService.ProgressInspectorService;
import com.example.tae.service.PurchaseService.ProgressInspectorServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogBuilder;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class ProgressInspectorController {
    private ProgressInspectorService progressInspectorService;

    @PostMapping("orderInspect")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> orderInspect(@RequestBody OrderInspectDTO inspect) throws  IllegalArgumentException  {
        ProgressInspectionDTO progressInspection = progressInspectorService.orderInsepector(inspect);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("progressInspection",progressInspection));
    }

    @GetMapping("inspectorData")
    public ResponseEntity<?> getProgressInspectorList(@RequestParam("planCode") int planCode) {
        List<ProgressInspectionDTO> progressInspectionList = progressInspectorService.getProgressInspectorList(planCode);
        progressInspectionList.forEach(x-> log.info(x.toString()));
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("progressInspectionList",progressInspectionList));
    }

    @PutMapping("inspectorNewDate")
    public ResponseEntity<?> updateProgressInspector(@RequestBody ProgressInspectionDTO progressInspectionDTO) {
        progressInspectorService.upDateProgressInspector(progressInspectionDTO.getProgressInspectionId(),progressInspectionDTO.getProgressInspectonDate());
        return ResponseEntity.ok().body("해당 진척검수를 수정하였습니다.");
    }

    @PutMapping("inspectorResult")
    public ResponseEntity<?> inspectorResult(@RequestBody ProgressInspectionDTO progressInspectionDTO) {
        List<String> result = progressInspectorService.inspectorResult(progressInspectionDTO.getProgressInspectionId(), progressInspectionDTO.isProgressInspectorResult(), progressInspectionDTO.getNote());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result",result));
    }

    @DeleteMapping("inspectorData/{progressInspectionId}")
    public void deleteProgressInspector(@PathVariable int progressInspectionId) {
            progressInspectorService.deleteProgressInspector(progressInspectionId);
    }

}
