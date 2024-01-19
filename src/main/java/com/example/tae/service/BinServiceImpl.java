package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BinServiceImpl implements BinService{

    ReceivingProcessingRepository receivingProcessingRepository;

    ProcurementPlanRepository procurementPlanRepository;


    @Override
    @Transactional
    public List<ReceivingProcessingDTO> procurementPlanList() {
        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST();
        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>();
        for (int i=0; i<procurementPlanList.size(); i++) {
            ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
                    .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
                    .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
                    .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                    .departName(procurementPlanList.get(i).getContract().getCompany().getBusinessName()) //업체명
                    .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
                    .DateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
                    .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
                    //.Arrival(receivingProcessingRepository.findByProcumentPlanCode(procurementPlanList.get(1).getProcurementplan_code()).getRegDate()) //입고처리된 날짜
                    .SupportProductAmount(procurementPlanList.get(i).getSupportProductAmount())
                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
                    .build();
                    receivingProcessingDTOList.add(receivingProcessingDTO);
        }

        return receivingProcessingDTOList;
    }

//    @Override
//    public List<ProcurementPlan> ProcumentPlanSearchList(String 보내는칸, String 검색종류, String 입고처리상태) {
//        return null;
//    }

    @Override
    @Transactional
    public void ReceivingProcessStore(int procurementplan_code, int store) {
        ReceivingProcessing receivingProcessing = ReceivingProcessing.builder()
                .procurementPlan(receivingProcessingRepository.productplane(procurementplan_code))
                .store(store)
                .build();
        receivingProcessingRepository.save(receivingProcessing); //입고처리 DB에 저장

        System.out.println("업데이트된 행의 갯수-------"+receivingProcessingRepository.updateProcumentPlan(procurementplan_code));//발주전을 마감으로

    }
}
