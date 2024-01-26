package com.example.tae.service;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.OrderRepository;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BinServiceImpl implements BinService{

    private ReceivingProcessingRepository receivingProcessingRepository;
    private ExistenceRepository existenceRepository;
    private OrderRepository orderRepository;
    private ContractRepository contractRepository;
    private ProcurementPlanRepository procurementPlanRepository;

    @Override //날짜를 입력받으면 현재 품목에 대해 리스트를 나타내주는 코드
    public List<ReceivingProcessingDTO> procurementPlanListbyStatement(Date start , Date end) {
        List<Object[]> procurementPlanList = receivingProcessingRepository.StatMentRepostSearch(start,end); //모든 품목에대한 조달계획 불러와서  리스트 에넣어주고<엔티티>
        List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>(); //반환해줄 DTO 리스트

        for (int i=0; i<procurementPlanList.size(); i++) { //품목에대한 조달계획 불러온 크기 만큼 반복 지금은 15 개내까 15개 반복
            ReceivingProcessing receivingProcessing;

            int aaa =(Integer)procurementPlanList.get(i)[0]; // 각각의 조달 꼐획 코드를 불러옴

            if (aaa!=0){
                 receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 aaa 인 엔티티의 정보를  하나 가져옴
                LocalDateTime localDateTime;

                if ( receivingProcessing == null) { // 조달계획 코드는 있는데 입고처리가 안된 것
                    localDateTime = LocalDateTime.of(1, 1, 1, 0, 0); // 임임의 날짜를 넣어줌
                } else {
                    localDateTime = receivingProcessing.getRegDate(); //입고처리 엔팉에서 가져온데이터
                }
//                procurementPlanList.forEach(j -> log.info("id : "+j[0].toString()+", sup : "+j[1].toString()+", orderDate : "+j[2].toString()+", orderState : "+j[3].toString()+", contractCode : "+j[4].toString()+
//                ", pjName : "+j[5]+", plandId : "+ j[6] + ", orderCode : "+j[7] + ", pjId : "+ j[8] + ", outNum : " + j[9] + ", outPutDate : " + j[10]
//                ));

                String orderCode;
                Purchase purchase=null;
                Optional<Purchase> optionalPurchase=null;

                if(procurementPlanList.get(i)[7]==null){

                }else {
                    orderCode = procurementPlanList.get(i)[7].toString();
                    optionalPurchase = orderRepository.findById(orderCode); //발주서코드로 찾고
                    purchase = optionalPurchase.get(); //발주서 엔티티 패치?
                }



                Optional<ProcurementPlan>  optionalProcurementPlan = procurementPlanRepository.findById((Integer)procurementPlanList.get(i)[0]); //조달계획 코드로 조달계획 찾아서 엔티티 불러오기
                ProcurementPlan procurementPlan = optionalProcurementPlan.get(); // 찾은 조달계획 엔티티

                LocalDateTime localDateTimePurchase=null;
                LocalDateTime localDateTimereciveing=null;
                if(purchase==null){
                    localDateTimePurchase=LocalDateTime.of(1, 1, 1, 0, 0);
                }else {
                    localDateTimePurchase=purchase.getRegDate();
                }


                ReceivingProcessingDTO addListDTO = ReceivingProcessingDTO.builder()
                        .procurementplan_code(procurementPlan.getContract().getProductInformationRegistration().getProduct_code()) //조달계획코드
                        .productcode(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())//품목코드
                        .productname(procurementPlan.getContract().getProductInformationRegistration().getProduct_name()) //품목명
                        .departName(procurementPlan.getContract().getCompany().getDepartName())//업체명
                        .businessNumber(procurementPlan.getContract().getCompany().getBusinessNumber())//사업자번호
                        .dateOfOrder(localDateTimePurchase)//발주서발행일
                        .orderDate(procurementPlan.getOrder_date())//발주일
                        .arrival(localDateTime)//입고일
                        .orderState(procurementPlan.getOrder_state())//품목처리상태
                        .build();
                receivingProcessingDTOList.add(addListDTO);
//                System.out.println(localDateTime);
            }

        }

        return receivingProcessingDTOList;


    }


    @Override
    @Transactional //날짜를 입력받으면 현재 품목에 대해 그래프를 그려주는 코드
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




    //////////////////////////////현황관리 서비스  끝//////////////////////////////////////////////////////////////////////////
    @Override
    @Transactional //입고처리 페이지 요청시 ----> 맨처음 화면을 리스트로 보여주는 것
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
                    .ordercode(procurementPlanList.get(i).getPurchase().getOrderCode()) // 발주서 코드번호
                    .procurementplan_code(procurementPlanList.get(i).getProcurementplan_code()) //조달계획코든
                    .productcode(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_code()) //품목코드
                    .productname(procurementPlanList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                    .departName(procurementPlanList.get(i).getContract().getCompany().getDepartName()) //업체명
                    .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
                    .dateOfOrder(procurementPlanList.get(i).getPurchase().getRegDate()) //발주서 발행일
                    .orderDate(procurementPlanList.get(i).getOrder_date())//발주일
                    .arrival(localDateTime) //입고처리된 날짜
                    .supportProductAmount(procurementPlanList.get(i).getSupportProductAmount())
                    .orderState(procurementPlanList.get(i).getOrder_state()) //품목상태
                    .build();
                    receivingProcessingDTOList.add(receivingProcessingDTO);
            System.out.println(localDateTime);
        }
        return receivingProcessingDTOList;
    }

    @Override
    public List<ReceivingProcessingDTO> search(String inputData,int searchStatenum) { //검색된 제품명으로 리스트
        List<ProcurementPlan> ppProductList=null; //조달계획 엔티티 리스트 불러와짐
        if (searchStatenum==1){
            ppProductList = receivingProcessingRepository.rPSearchByProductname(inputData); //제품명으로
        }else if(searchStatenum==2){
            ppProductList = receivingProcessingRepository.rPSearchByDepartname(inputData); //회사명으로
        }else {
            ppProductList=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST(); //0번은 전체
        }

        List<ReceivingProcessingDTO> returnList = new ArrayList<>();
        System.out.println("asdsadsadasdasdasdasdsdsadasdasasasdas 리스트 사이드 "+ppProductList.size());
        for (int i = 0; i<ppProductList.size(); i++){ // 엔티티 리스트 만큼 반복해주고

            int aaa=ppProductList.get(i).getProcurementplan_code();// 조달계획코드
            ReceivingProcessing receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 1 이고 등록된낮라
            LocalDateTime localDateTime;
            if ( receivingProcessing == null) {
                localDateTime = LocalDateTime.of(1, 1, 1, 0, 0);
            } else {
                localDateTime = receivingProcessing.getRegDate();
            }
            ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
                    .ordercode(ppProductList.get(i).getPurchase().getOrderCode()) // 발주서 코드번호
                    .procurementplan_code(ppProductList.get(i).getProcurementplan_code()) //조달계획코든
                    .productcode(ppProductList.get(i).getContract().getProductInformationRegistration().getProduct_code()) //품목코드
                    .productname(ppProductList.get(i).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                    .departName(ppProductList.get(i).getContract().getCompany().getDepartName()) //업체명
                    .businessNumber(ppProductList.get(i).getContract().getCompany().getBusinessNumber()) // 사업자번호
                    .dateOfOrder(ppProductList.get(i).getPurchase().getRegDate()) //발주서 발행일
                    .orderDate(ppProductList.get(i).getOrder_date())//발주일
                    .arrival(localDateTime) //입고처리된 날짜
                    .supportProductAmount(ppProductList.get(i).getSupportProductAmount())
                    .orderState(ppProductList.get(i).getOrder_state()) //품목상태
                    .build();
            returnList.add(receivingProcessingDTO);

        }
        return returnList;
    }



    @Override
    @Transactional  //입고처리에서 값을 넣고 요청시 입고처리를 한후 새로운 리스트를 그릴수있게 AJAX DTO 리스트를 보내 주는 코드 ---> 맨처음 화면시
    public List<ReceivingProcessingDTO> ReceivingProcessStore(int procurementplan_code, int store,int pageState ,String formInputData) {

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


        List<ProcurementPlan> ppProductList=null; //조달계획 엔티티 리스트 불러와짐 //다시 그려주기 위해서
        if (pageState==1){
            ppProductList = receivingProcessingRepository.rPSearchByProductname(formInputData); //제품명으로
        }else if(pageState==2){
            ppProductList = receivingProcessingRepository.rPSearchByDepartname(formInputData); //회사명으로
        }else {
            ppProductList=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST(); //0번은 전체
        }
        System.out.println("입고처리후 리스트를 그려줄떄ppProductList========> 리스트 사이즈 크기는?"+ppProductList.size());
        List<ReceivingProcessingDTO> returnList = new ArrayList<>();
        for (int n=0; n<ppProductList.size(); n++) {
            int aaa=ppProductList.get(n).getProcurementplan_code();// 조달계획코드를 가져옴

            ReceivingProcessing receivingProcessingBin = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획코드를 넣어 줘서 입고처리 엔티티의 한행을 가져옴
            LocalDateTime localDateTime;
            if ( receivingProcessingBin == null) {
                localDateTime = LocalDateTime.of(1, 1, 1, 0, 0);
            } else {
                localDateTime = receivingProcessingBin.getRegDate();
            }
            ReceivingProcessingDTO addListDTO = ReceivingProcessingDTO.builder()
                    .ordercode(ppProductList.get(n).getPurchase().getOrderCode()) // 발주서 코드번호
                    .procurementplan_code(ppProductList.get(n).getProcurementplan_code()) //조달계획코든
                    .productcode(ppProductList.get(n).getContract().getProductInformationRegistration().getProduct_code()) //품목코드
                    .productname(ppProductList.get(n).getContract().getProductInformationRegistration().getProduct_name())   //품목명
                    .departName(ppProductList.get(n).getContract().getCompany().getDepartName()) //업체명
                    .businessNumber(ppProductList.get(n).getContract().getCompany().getBusinessNumber()) // 사업자번호
                    .dateOfOrder(ppProductList.get(n).getPurchase().getRegDate()) //발주서 발행일
                    .orderDate(ppProductList.get(n).getOrder_date())//발주일
                    .arrival(localDateTime) //입고처리된 날짜
                    .supportProductAmount(ppProductList.get(n).getSupportProductAmount()) //조달예정수량
                    .orderState(ppProductList.get(n).getOrder_state()) //품목상태
                    .build();
            System.out.println("리스트에 최종적으로 더해진 숫잣"+n);
            returnList.add(addListDTO);
//            System.out.println("------------------SAVE   DTO에들어간 입고일 정보----------------------------"+localDateTime);

//            System.out.println("리스트에 들어간 SAVE DTO 정보---------------------------------------"+ppProductList.get(i).getPurchase().getOrderCode());
        }

        returnList.forEach(x-> System.out.println("이리스트 안에 있는 내용은 몇개일까?"+x));

        return returnList;
    }
}
