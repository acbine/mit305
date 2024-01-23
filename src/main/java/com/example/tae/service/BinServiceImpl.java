package com.example.tae.service;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
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

    private ReceivingProcessingRepository receivingProcessingRepository;

    private ProcurementPlanRepository procurementPlanRepository;
    private ProductInformationRegistrationRepository productInformationRegistrationRepository;
    private ExistenceRepository existenceRepository;

    @Override
    public List<ReceivingProcessingDTO> procurementPlanListbyStatement(Date start , Date end) {
        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.StatMentRepostSearch(start,end); //모든 품목에대한 조달계획 불러와서  리스트 에넣어주고<엔티티>
        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>(); //반환해줄 DTO 리스트
        System.out.println("dlllllllllllllllllllll        리스트 사이즈       llllllllllllllllll"+procurementPlanList.size());
        System.out.println("for문이 바로 아래에 있음");

        for (int i=0; i<procurementPlanList.size(); i++) { //품목에대한 조달계획 불러온 크기 만큼 반복 지금은 15 개내까 15개 반복
            System.out.println("/////////////////////////////////////////////"+procurementPlanList.size());
            ReceivingProcessing receivingProcessing;
            List<ProcurementPlan> bbb=receivingProcessingRepository.statementAllSearch();// 모든 조달 계획 품목을 불러옴
            int aaa =bbb.get(i).getProcurementplan_code(); // 각각의 조달 꼐획 코드를 불러옴

            System.out.println(aaa);
            if (aaa!=0){
                 receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 aaa 인 엔티티의 정보를  하나 가져옴
                LocalDateTime localDateTime;
                System.out.println("++++++++++++여기까지는 잘됨ㅁ+++++++++");

                if ( receivingProcessing == null) { // 조달계획 코드는 있는데 입고처리가 안된 것
                    localDateTime = LocalDateTime.of(1, 1, 1, 0, 0); // 임임의 날짜를 넣어줌
                } else {
                    localDateTime = receivingProcessing.getRegDate(); //입고처리 엔팉에서 가져온데이터
                }

                Purchase purchase = procurementPlanList.get(i).getPurchase();
                LocalDateTime localDateTimePurchase=null;

                if(purchase==null){
                    localDateTimePurchase=LocalDateTime.of(1, 1, 1, 0, 0);
                }else {
                    localDateTimePurchase=procurementPlanList.get(i).getPurchase().getRegDate();
                }

                System.out.println("++++++++++++//////////////////////////////////////////////////////+++++++++");
                ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
                        .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
                        .productcode(procurementPlanList.get(i).getProductForProject().getProductCode().getProduct_code()) //품목코드
                        .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                        .departName(procurementPlanList.get(i).getContract().getCompany().getDepartName()) //업체명
                        .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호

                        .DateOfOrder(localDateTimePurchase) //발주서 발행일 수정되게 수정

                        .OrderDate(procurementPlanList.get(i).getOrder_date())//발주일
                        .Arrival(localDateTime) //입고처리된 날짜
                        .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
                        .build();
                receivingProcessingDTOList.add(receivingProcessingDTO);
                System.out.println(localDateTime);
            }
        }
        return receivingProcessingDTOList;

    }
//////////////////////////////현황관리 서비스//////////////////////////////////////////////////////////////////////////
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
            int aaa=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST().get(i).getProcurementplan_code();// 조달계획코드
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

    @Override
    @Transactional
    public List<ReceivingProcessingDTO> ReceivingProcessStore(int procurementplan_code, int store) {
        ReceivingProcessing receivingProcessing = ReceivingProcessing.builder()
                .procurementPlan(receivingProcessingRepository.productplane(procurementplan_code))
                .store(store)
                .build();
        ProductInformationRegistration productInformationRegistration = receivingProcessing.getProcurementPlan().getContract().getProductInformationRegistration();
        Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                ()->{
                    Existence existence1 = Existence.builder()
                            .productCode(productInformationRegistration)
                            .releaseCNT(store)
                            .build();
                    existenceRepository.save(existence1);
                    return existence1;
                }
        ));
        existenceRepository.save(existence.get().updateRelease(receivingProcessing.getStore()));
        receivingProcessingRepository.save(receivingProcessing); //입고처리 DB에 저장
        receivingProcessingRepository.updateProcumentPlan(procurementplan_code);//검수완료를  마감으로
//        System.out.println("업데이트된 행의 갯수-------"+receivingProcessingRepository.updateProcumentPlan(procurementplan_code));//발주전을 마감으로

        List<ProcurementPlan> procurementPlanList = receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST(); //다시 조달계획을 그려주기위하여 받음
        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>();
        for (int i=0; i<procurementPlanList.size(); i++) {
            int aaa=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST().get(0).getProcurementplan_code();// 조달계획코드
            ReceivingProcessing receivingProcessing2 = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 1 이고 등록된낮라
            LocalDateTime localDateTime;
            if ( receivingProcessing2 == null) {
                localDateTime = LocalDateTime.of(1, 1, 1, 0, 0);
            } else {
                localDateTime = receivingProcessing2.getRegDate();
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
                    .SupportProductAmount(procurementPlanList.get(i).getSupportProductAmount()) //조달예정수량
                    //.store(receivingProcessingRepository.findByProcumentPlanCode(aaa).getStore())
                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
                    .build();
            receivingProcessingDTOList.add(receivingProcessingDTO);
            System.out.println(localDateTime);
        }

        return receivingProcessingDTOList;

    }
}
