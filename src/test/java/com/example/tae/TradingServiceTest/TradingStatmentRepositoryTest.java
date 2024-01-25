package com.example.tae.TradingServiceTest;

import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.repository.ReceivingProcessingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TradingStatmentRepositoryTest {

    @Autowired
    ReceivingProcessingRepository receivingProcessingRepository;

    @Test
    public void repotest(){
        //ReceivingProcessing tradingStatement= TradingStatement.builder().store(9).build();
        //tradingStatmentRepository.save(tradingStatement);


        System.out.println("------------------");

        List<ReceivingProcessing> tradingStatementList =receivingProcessingRepository.findAll();
        tradingStatementList.forEach(x-> System.out.println(x.getStore()));
        System.out.println("---------------------------------");
        System.out.println(receivingProcessingRepository.findById(6).get().getStore());


    }

    @Test
    @Transactional
    public void orderbyTest(){
//        public List<TradingStatementDTO> showListOrderByorderCode() {
//            List<Object[]> listOrderCode=receivingProcessingRepository.groupByOrderCode(); // 발주서 코드 및 그에해당하는 갯수 전부 불러오기 String 과 Long
//            List<TradingStatementDTO> listTrading = new ArrayList<>(); //리턴할 리스트
//
//            for(int i=0; i<listOrderCode.size(); i++){ //리스트 크기가 2 니까 2번 반복
//                List<ProcurementPlan> procurementPlanList =receivingProcessingRepository.listByOrderCode2((String)listOrderCode.get(i)[0]); //발주서 코드로 발주서  품목의 상태가 마감 인 품목찾음
//                List<ReceivingProcessingDTO> receivingProcessingDTOList = new ArrayList<>(); // 발주서 코드로 찾음 품목들이 들어간 리스트
//
//                if((long)procurementPlanList.size()==(Long)listOrderCode.get(i)[1]){ //조달계획의 품목 사이즈와 각  발주서코드해당하는 갯수 를 비교 같야야 출력
//
//                    for (int a=0; a<procurementPlanList.size(); a++) { //발주서 코드로 품목의 상태만가 마감인 품목을 찾은 리스트 -> 크기만큰 for 문돌려줌
//                        int aaa=receivingProcessingRepository.RECEIVING_PROCESSING_DTO_LIST().get(a).getProcurementplan_code();// 조달계획코드
//                        ReceivingProcessing receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(aaa); //조달계획이 1 이고 등록된낮라
//                        LocalDateTime localDateTime;
//                        if ( receivingProcessing == null) { //엔티티가 널일 경우
//                            localDateTime = LocalDateTime.of(1, 1, 1, 0, 0); // 비어있을경우 여기서 입고일을 대충생성
//                        } else {
//                            localDateTime = receivingProcessing.getRegDate(); // 비어있지 않을경우 입고일을 가져옴
//                        } // 입고일 찾아줌
//                        ReceivingProcessingDTO receivingProcessingDTO = ReceivingProcessingDTO.builder()
//                                .procurementplan_code(procurementPlanList.get(a).getProcurementplan_code()) //조달계획코든
//                                .productcode(procurementPlanList.get(a).getProductForProject().getProductCode().getProduct_code()) //품목코드
//                                .productname(procurementPlanList.get(a).getContract().getProductInformationRegistration().getProduct_name())   //품목명
//                                .departName(procurementPlanList.get(a).getContract().getCompany().getDepartName()) //업체명
//                                .businessNumber(procurementPlanList.get(a).getContract().getCompany().getBusinessNumber()) // 사업자번호
//                                .DateOfOrder(procurementPlanList.get(a).getPurchase().getRegDate()) //발주서 발행일
//                                .OrderDate(procurementPlanList.get(a).getOrder_date())//발주일
//                                .Arrival(localDateTime) //입고처리된 날짜
//                                .SupportProductAmount(procurementPlanList.get(a).getSupportProductAmount())
//                                .orderState(procurementPlanList.get(a).getOrder_state()) //품목상태
//                                .build();
//                        receivingProcessingDTOList.add(receivingProcessingDTO); //DTO 객체를 만들어준후 넣어줌
//                    }
//
//                    TradingStatementDTO tradingStatementDTO = TradingStatementDTO.builder()
//                            .dtoList(receivingProcessingDTOList)
//                            .orderCode((String)listOrderCode.get(i)[0])
//                            .businessNumber(procurementPlanList.get(i).getContract().getCompany().getBusinessNumber())
//                            .departName(procurementPlanList.get(i).getContract().getCompany().getDepartName())
//                            .businessName(procurementPlanList.get(i).getContract().getCompany().getBusinessName())
//                            .businessTel(procurementPlanList.get(i).getContract().getCompany().getBusinessTel())
//                            .businessEmail(procurementPlanList.get(i).getContract().getCompany().getBusinessEmail())
//                            .fax(procurementPlanList.get(i).getContract().getCompany().getFax())
//                            .build();
//                    listTrading.add(tradingStatementDTO);
//
//                } // 같아야지 품목 DTO가 나오고 나온거를  리스트로 만들어줌
//
//
//            }
//
//            return listTrading;
//        }


    }
}
