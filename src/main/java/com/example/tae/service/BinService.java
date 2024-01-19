package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;

import java.util.List;

/**
 * 현황 관리 에 관한 서비스및 입고처리에 관한 서비스
 */
public interface BinService {
    //----------------------------------현황관리------------------------------------------





    //----------------------------------입고처리---------------------------------------------
    //입고처리 홈페이지 요청시 조달계획의 품목 리스트 불러오기
    List<ReceivingProcessingDTO> procurementPlanList();

    //입고처리 홈페이지에서 검색 요청시 검색된 조달계획의 품목 리스트 불러오기
    //List<ProcurementPlan> ProcumentPlanSearchList(String 보내는칸 , String 검색종류 , String 입고처리상태);

    //해당 품목에대해 입고처리시 쿼리로 입고수량 데이터 베이스에 넣어어주는것 (각 품목에대한 조달계획이없어 일단 입고수량만 넣음)
    void ReceivingProcessStore(int procurementplan_code, int store);
}
