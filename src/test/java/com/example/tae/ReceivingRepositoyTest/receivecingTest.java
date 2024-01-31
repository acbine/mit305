package com.example.tae.ReceivingRepositoyTest;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.service.BinService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class receivecingTest {

//    @Autowired
//    ReceivingProcessingRepository receivingProcessingRepository;
//
//    @Autowired
//    BinService binServices;
//
//    @Test
//    @Transactional
//    public void aa(){
//
//        List<ProcurementPlan> aalist= receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST();
//
//        //aalist.forEach(x-> System.out.println("---------------------------"+x));
//        System.out.println(aalist.get(0).getSupportProductAmount());
//        //aalist.get(1).getProductForProject()
//
//    }
//
//    @Test
//    public void bb(){
//        System.out.println("시작");
//        binServices.procurementPlanList();
//
//    }
//
//    @Test
//    @Transactional
//    public void bbss(){
//
//        receivingProcessingRepository.findByProcumentPlanCode(2);
//        System.out.println(receivingProcessingRepository.findByProcumentPlanCode(2).getProcurementPlan());
//
//
//    }
//
//    @Test
//    public void asdas(){
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            Date changedStartDate; //Date 타입 시작 날짜
//            Date changedEndDate; //Date 타입 끝날자
//
//            changedStartDate = sdf.parse("1000-12-31");
//            changedEndDate = sdf.parse("5000-12-31");
//            List<Object[]> resultList = receivingProcessingRepository.groupByOrderState (changedStartDate, changedEndDate);
//
//            for (Object[] asd : resultList){
//                String orderState = (String) asd[0];
//                Long count = (Long) asd[1];
//                System.out.println("--------상태값"+orderState+"-----------숫자--------------------"+count);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @Test
//    public void aaa(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date changedStartDate; //Date 타입 시작 날짜
//            Date changedEndDate; //Date 타입 끝날자
//            String startDate="";
//            String endDate="";
//                if (startDate.isEmpty()) {
//                    changedStartDate = sdf.parse("1000-12-31");
//                } else {
//                    changedStartDate = sdf.parse(startDate);
//                }
//                if (endDate.isEmpty()) {
//                    changedEndDate = sdf.parse("5000-12-31");
//                } else {
//                    changedEndDate = sdf.parse(endDate);
//                }
//            List<Object[]> procurementPlanList = receivingProcessingRepository.StatMentRepostSearch(changedStartDate,changedEndDate); //모든 품목에대한 조달계획 불러와서  리스트 에넣어주고<엔티티>
//            List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>(); //반환해줄 DTO 리스트
//            System.out.println("for문이 바로 아래에 있음");
//            System.out.println("dlllllllllllllllllllll 왜 비었을까????llllllllllllllllll"+procurementPlanList.size());
//            Integer sd=procurementPlanList.size();
//            for (int i=0; i<sd; i++) { //품목에대한 조달계획 불러온 크기 만큼 반복
//                System.out.println("asdsadsadsasdsadaasasd");
//                Integer aaa=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST().get(0).getProcurementplan_code();//모든 품목에대한  조달계획코드 를 넣어줌 하나씩 aaa에 넣어줌 null=0;
//                System.out.println("assssssssssssssssssssssssssssssssssss"+aaa);
////                if (aaa!=0){
////                    receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 1 이고 등록된낮라
////                    LocalDateTime localDateTime;
////                    System.out.println("++++++++++++여기까지는 잘됨ㅁ+++++++++");
////                    if ( receivingProcessing == null) {
////                        localDateTime = LocalDateTime.of(1, 1, 1, 0, 0);
////                    } else {
////                        localDateTime = receivingProcessing.getRegDate();
////                    }
////                System.out.println("++++++++++++//////////////////////////////////////////////////////+++++++++");
////                ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
////                        .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
////                        .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
////                        .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
////                        .departName(procurementPlanList.get(i).getContract().getCompany().getDepartName()) //업체명
////                        .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
////                        .DateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
////                        .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
////                        .Arrival(localDateTime) //입고처리된 날짜
////                        .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
////                        .build();
////                receivingProcessingDTOList.add(receivingProcessingDTO);
////                System.out.println(localDateTime);
//
//                }
//        }catch(Exception e){
//
//        }
//    }
//
//    @Test
//    @Transactional
//    public void sadaz(){ //품목명 검색 쿼리 테스트
//        List<ProcurementPlan> sdas=receivingProcessingRepository.rPSearchByProductname("액정");
//        System.out.println("----------------------------------------------");
//        sdas.forEach(x-> System.out.println(x.getProcurementplan_code()+x.getContract().getProductInformationRegistration().getProduct_name()));
//        System.out.println("----------------------------------------------");
////        System.out.println("+++++++++++++++++++++"+sdas.get(0).getProcurementplan_code());
//
//    }
//
//    @Test
//    @Transactional
//    public void sadasad(){ //회사면 검색 쿼리테스트
//        List<ProcurementPlan> sdas=receivingProcessingRepository.rPSearchByDepartname("부석종합건설(주)");
//        System.out.println("----------------------시작------------------------");
//        sdas.forEach(x-> System.out.println(x.getProcurementplan_code()+x.getContract().getCompany().getDepartName()));
//        System.out.println("----------------------끝------------------------");
////        System.out.println("+++++++++++++++++++++"+sdas.get(0).getProcurementplan_code());
//
//    }
//



}


