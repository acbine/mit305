package com.example.tae.controller;


import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;
import com.example.tae.service.PurchaseService.ProgressInspectorService;
import com.example.tae.service.PurchaseService.ProgressInspectorServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogBuilder;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class ProgressInspectorController {
    private ProgressInspectorService progressInspectorService;

    @PostMapping("orderInspect")
    public ResponseEntity<?> orderInspect(@RequestBody OrderInspectDTO inspect) throws  IllegalArgumentException  {
        ProgressInspectionDTO progressInspection = progressInspectorService.orderInsepector(inspect);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("progressInspection",progressInspection));
    }

    @GetMapping("inspectorData")
    public ResponseEntity<?> getProgressInspectorList(@RequestParam("planCode") int planCode) {
        List<ProgressInspectionDTO> progressInspectionList = progressInspectorService.getProgressInspectorList(planCode);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("progressInspectionList",progressInspectionList));
    }

    @PutMapping("inspectorNewDate")
    public ResponseEntity<?> updateProgressInspector(@RequestBody ProgressInspectionDTO progressInspectionDTO) {
        log.info(progressInspectionDTO.getProgressInspectionId()+" 받아오는 아이디 정보와 데이터 정보 확인하기"+progressInspectionDTO.getProgressInspectonDate().toString());
        progressInspectorService.upDateProgressInspector(progressInspectionDTO.getProgressInspectionId(),progressInspectionDTO.getProgressInspectonDate());
        return ResponseEntity.ok().body("해당 진척검수를 수정하였습니다.");
    }

    @DeleteMapping("inspectorData")
    public void deleteProgressInspector() {

    }
}
