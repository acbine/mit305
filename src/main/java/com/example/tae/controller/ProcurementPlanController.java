package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Classification.Assy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ProcurementPlanController {

    // 조달 계획 페이지 요청
    @GetMapping("ProcurementPlanRegistration")
    public String ProcurementPlanRegistration() {
        return "ProcurementPlanRegistration";
    }

    // 계약에 있는 한가지 품목 코드를 가지고 품목 정보 에서 품목명을 검색 하여 결과 데이터 리턴


    // 계약에 있는 한가지 품목 코드를 가지고 계약 정보에서 검색 하여 L/T와 조달 업체명을


    // 계약에 있는 한가지 품목 코드를 가지고 생산 계획 에서 검색 하여 (재고 수량, 제품 생산 날짜, 필요수량(제품 생산량 * 품목 필요양)) 결과 데이터를 리턴
    @PostMapping("/asd")
    @ResponseBody
    public ResponseEntity<Contract> asd() {

        Contract contract = new Contract();

        return ResponseEntity.status(HttpStatus.OK).body(contract);
    }


}
