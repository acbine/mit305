package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BinServiceImpl implements BinService{

    ReceivingProcessingRepository receivingProcessingRepository;

    ProcurementPlanRepository procurementPlanRepository;

    @Override
    public List<ReceivingProcessingDTO> procurementPlanListbyStatement(Date start , Date end) {
        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.StatMentRepostSearch(start,end); //모든 조달꼐획 리스트<엔티티>
        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>();
        for (int i=0; i<procurementPlanList.size(); i++) {
            int aaa=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST().get(0).getProcurementplan_code();// 조달계획코드
            ReceivingProcessing receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 1 이고 등록된낮라
            LocalDateTime localDateTime;
            if ( receivingProcessing == null) {
                localDateTime = LocalDateTime.of(1, 1, 1, 0, 0);
            } else {
                localDateTime = receivingProcessing.getRegDate();
            }
            ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
                    .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
                    .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
                    .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                    .departName(procurementPlanList.get(i).getContract().getCompany().getDepartName()) //업체명
                    .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
                    .DateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
                    .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
                    .Arrival(localDateTime) //입고처리된 날짜
                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
                    .build();
            receivingProcessingDTOList.add(receivingProcessingDTO);
            System.out.println(localDateTime);
        }

        return receivingProcessingDTOList;

    }

    @Override
    @Transactional
    public List<StatusManagementDTO> statusManagementDTOList(Date start, Date end) {
        List<Object[]> ObjectList=receivingProcessingRepository.groupByOrderState(start,end);
        List<StatusManagementDTO> StatusManagementDTOList= new ArrayList<>();
        for (Object[] asd : ObjectList){
            StatusManagementDTO dto = StatusManagementDTO.builder().orderStateForSMString((String) asd[0]).count((Long) asd[1]).build();
            System.out.println("--------들어간 목록 종류"+(String) asd[0]+"-----------들어간 숫자--------------------"+(Long) asd[1]);
            StatusManagementDTOList.add(dto);
        }
        return StatusManagementDTOList;
    }

    @Override
    @Transactional
    public List<ReceivingProcessingDTO> procurementPlanList() {
        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST();
        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>();
        for (int i=0; i<procurementPlanList.size(); i++) {
            int aaa=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST().get(0).getProcurementplan_code();// 조달계획코드
            ReceivingProcessing receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 1 이고 등록된낮라
            LocalDateTime localDateTime;
            if ( receivingProcessing == null) {
                localDateTime = LocalDateTime.of(1, 1, 1, 0, 0);
            } else {
                localDateTime = receivingProcessing.getRegDate();
            }
            ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
                    .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
                    .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
                    .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                    .departName(procurementPlanList.get(i).getContract().getCompany().getDepartName()) //업체명
                    .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
                    .DateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
                    .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
                    .Arrival(localDateTime) //입고처리된 날짜
                    .SupportProductAmount(procurementPlanList.get(i).getSupportProductAmount())
                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
                    .build();
                    receivingProcessingDTOList.add(receivingProcessingDTO);
            System.out.println(localDateTime);
        }

        return receivingProcessingDTOList;
    }

//    @Override //품목상태와 회사명 으로 검색
//    public List<ReceivingProcessingDTO> ProcumentPlanSearchPNOSList(String inputData, String searchState) {
//        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.searchProcurementPlansBNOS(searchState,inputData);
//        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>();
//        for (int i=0; i<procurementPlanList.size(); i++) {
//            ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
//                    .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
//                    .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
//                    .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
//                    .departName(procurementPlanList.get(i).getContract().getCompany().getBusinessName()) //업체명
//                    .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
//                    .DateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
//                    .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
//                    //.Arrival(receivingProcessingRepository.findByProcumentPlanCode(procurementPlanList.get(1).getProcurementplan_code()).getRegDate()) //입고처리된 날짜
//                    .SupportProductAmount(procurementPlanList.get(i).getSupportProductAmount())
//                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
//                    .build();
//            receivingProcessingDTOList.add(receivingProcessingDTO);
//        }
//
//        return receivingProcessingDTOList;
//    }

//    @Override //품목상태와 제품명 으로 검색
//    public List<ReceivingProcessingDTO> ProcumentPlanSearchBNOSList(String inputData, String searchState) {
//        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.searchProcurementPlansBNOS(searchState,inputData);
//        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>();
//        for (int i=0; i<procurementPlanList.size(); i++) {
//            ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
//                    .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
//                    .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
//                    .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
//                    .departName(procurementPlanList.get(i).getContract().getCompany().getBusinessName()) //업체명
//                    .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
//                    .DateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
//                    .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
//                    //.Arrival(receivingProcessingRepository.findByProcumentPlanCode(procurementPlanList.get(1).getProcurementplan_code()).getRegDate()) //입고처리된 날짜
//                    .SupportProductAmount(procurementPlanList.get(i).getSupportProductAmount())
//                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
//                    .build();
//            receivingProcessingDTOList.add(receivingProcessingDTO);
//        }
//
//        return receivingProcessingDTOList;
//
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
