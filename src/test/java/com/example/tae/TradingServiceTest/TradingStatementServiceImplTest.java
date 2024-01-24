package com.example.tae.TradingServiceTest;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.TradingStatement.TradingStatementModalDTO;
import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.service.TradingStatementService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TradingStatementServiceImplTest {

    @Autowired
    TradingStatementService tradingStatementService;
    @Autowired
    ReceivingProcessingRepository receivingProcessingRepository;

    @Test
    public void tradingStatementServiceTest(){
        //구름 64 이미지
        String cloud64="data:image/jpeg;base64,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";
        ImageDTO imageDTO = ImageDTO.builder().ordercode("123214").imageDataURL(cloud64).emailadress("bonaeneunyong@gmail.com").build();
        tradingStatementService.imageUpload(imageDTO);
        System.out.println("메일보내는 테스트 코드 성공");
    }





    @Test
    @Transactional
    public void aaaTest(){

//        List<Object[]> groupByOrderCodeList=receivingProcessingRepository.groupByOrderCode(); // 발주서 코드 및 그에해당하는 갯수 전부 불러오기 String 과 Long
//        System.out.println("++++++발주서 코드UUID+++++++"+groupByOrderCodeList.get(0)[0]);
//        System.out.println("-------이코드르 가진 UUID 4개 ------"+groupByOrderCodeList.get(0)[1]+"개");
//
//        System.out.println("++++++발주서 코드UUID+++++++"+groupByOrderCodeList.get(1)[0]);
//        System.out.println("-------이코드르 가진 UUID 11개 ------"+groupByOrderCodeList.get(1)[1]+"개");
//
//        System.out.println("===========리스트의 크기는 0,1 2개================="+groupByOrderCodeList.size());
//
//        for(int i=0; i<groupByOrderCodeList.size(); i++){ //리스트 크기가 2 니까 2번 반복
//
//            List<ProcurementPlan> procurementPlanListend =receivingProcessingRepository.listByOrderCodeend((String)groupByOrderCodeList.get(i)[0]); //발주서 코드로 발주서  품목의 상태가 마감 인 품목찾음
//            List<ProcurementPlan> procurementPlanListstart =receivingProcessingRepository.listByOrderCodestrard((String)groupByOrderCodeList.get(i)[0]); //발주서 코드로 발주서  품목의 상태가 마감 인 품목찾음
//            System.out.println(i+"번쨰겄 "+procurementPlanListend.size());
////
//            if(procurementPlanListstart.size() == procurementPlanListend.size() ){ //조달계획의 품목 사이즈와 각  발주서코드해당하는 갯수 를 비교 같야야 출력
//  //                 4개   ->  다음 11개
//                System.out.println("통과 UUID는"+procurementPlanListend.get(i).getPurchase().getOrdercode());
//
//
//            } // 같아야지 품목 DTO가 나오고 나온거를  리스트로 만들어줌
//            else {
//                System.out.println("실패 UUID는"+procurementPlanListend.get(i).getPurchase().getOrdercode());
//            }
//
//        }
        List<Object[]> groupByOrderCodeList=receivingProcessingRepository.groupByOrderCode(); // 발주서 코드 및 그에해당하는 갯수 전부 불러오기 String 과 Long
        List<TradingStatementModalDTO> retrunList = new ArrayList<>(); // 리턴용 리스트


//
        for(int i=0; i<groupByOrderCodeList.size(); i++) { //리스트 크기가 5 니까 5번 반복
            System.out.println(i);

            System.out.println(i+"반복횟수==================================");

            System.out.println("UUID는-*******************************" + (String) groupByOrderCodeList.get(i)[0]);
            List<ProcurementPlan> procurementPlanListend = receivingProcessingRepository.listByOrderCodeend((String) groupByOrderCodeList.get(i)[0]); //발주서 코드로 발주서  품목의 상태가 마감 인 품목찾음
            System.out.println("마감   -------------------------- 리스트 크기?" + procurementPlanListend.size());

            if ((long) groupByOrderCodeList.get(i)[1] == (long) procurementPlanListend.size()) { //조달계획의 품목 사이즈와 각  발주서코드해당하는 갯수 를 비교 같야야 출력
                //
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++" + i + "번쨰 인덱스UUID는" + groupByOrderCodeList.get(i)[0] + "    이UUID를 가지고있는 품목의 갯수    " + groupByOrderCodeList.get(i)[1]);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++UUID가 " + (String) groupByOrderCodeList.get(i)[0] + "이거인 목록중에 리스트의 크기는" + procurementPlanListend.size());
//                System.out.println("통과 UUID는" + procurementPlanListend.get(i).getPurchase().getOrdercode());

//                for (int b = 0; b < procurementPlanListend.size(); b++) {
//                    System.out.println(b + "번 반복------------------------------------------------------------------");
//
//                    TradingStatementModalDTO dto = TradingStatementModalDTO.builder()
//                            .orderCode(procurementPlanListend.get(b).getPurchase().getOrdercode())
//                            .prouctName(procurementPlanListend.get(b).getContract().getProductInformationRegistration().getProduct_name())
//                            .count(procurementPlanListend.get(b).getContract().getProduct_price()) //입고처리에서 가져와야함
//                            //.price(receivingProcessingRepository.findByProcumentPlanCode(procurementPlanListend.get(b).getContract().getProductInformationRegistration().getProduct_code()).getStore())
//                            //.pc(receivingProcessingRepository.findByProcumentPlanCode(procurementPlanListend.get(b).getContract().getProductInformationRegistration().getProduct_code()).getStore()*procurementPlanListend.get(b).getContract().getProduct_price())
//                            .businessNumber(procurementPlanListend.get(b).getContract().getCompany().getBusinessNumber())
//                            .departName(procurementPlanListend.get(b).getContract().getCompany().getDepartName())
//                            .businessName(procurementPlanListend.get(b).getContract().getCompany().getBusinessName())
//                            .businessEmail(procurementPlanListend.get(b).getContract().getCompany().getBusinessEmail())
//                            .fax(procurementPlanListend.get(b).getContract().getCompany().getFax())
//                            .businessTel(procurementPlanListend.get(b).getContract().getCompany().getBusinessTel())
//                            .build();
//                    retrunList.add(dto);
//                }

            } // 같아야지 품목 DTO가 나오고 나온거를  리스트로 만들어줌
            else {

            }


        }

    }


    @Test
    public void asad2() {

        List<Object[]> groupByOrderCodeList=receivingProcessingRepository.groupByOrderCode(); // 발주서 코드 및 그에해당하는 갯수 전부 불러오기 String 과 Long
        List<TradingStatementModalDTO> retrunList = new ArrayList<>(); // 리턴용 리스트

        System.out.println("발주서 코드의 행은 5행"+groupByOrderCodeList.size());

        List<String> uuidlist = new ArrayList<>();

        for (int i = 0; i < groupByOrderCodeList.size(); i++) { //리스트 크기가 5 니까 5번 반복
            System.out.println(i + "반복횟수==================================");

            System.out.println("UUID는-*******************************" + (String) groupByOrderCodeList.get(i)[0]);
            List<ProcurementPlan> procurementPlanListend = receivingProcessingRepository.listByOrderCodeend((String) groupByOrderCodeList.get(i)[0]); //발주서 코드로 발주서  품목의 상태가 마감 인 품목찾음
            System.out.println("마감   --------------------발주마감 된것만 불러온------ 리스트 크기?" + procurementPlanListend.size());

            if ((long) groupByOrderCodeList.get(i)[1] == (long) procurementPlanListend.size()) { //조달계획의 품목 사이즈와 각  발주서코드해당하는 갯수 를 비교 같야야 출력
//              여기서 알수있는정보는 발주서 코드가얼마나 몇개 있는지    ::: 그발주서 코드에 해당하는 정보와 발주서 그게 몇게 있는지(발주서코드는 상단에서 바뀜)
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++" + i + "번쨰 인덱스UUID는" + groupByOrderCodeList.get(i)[0] + "    이UUID를 가지고있는 품목의 갯수    " + groupByOrderCodeList.get(i)[1]);
                System.out.println("***************************UUID가 " + (String) groupByOrderCodeList.get(i)[0] + "이거인 목록중에 리스트의 크기는" + procurementPlanListend.size());
                System.out.println("통과 UUID는" + procurementPlanListend.get(0).getPurchase().getOrdercode());
                uuidlist.add(procurementPlanListend.get(0).getPurchase().getOrdercode());
            } // 같아야지 품목 DTO가 나오고 나온거를  리스트로 만들어줌
            else {
//                System.out.println("불통과한  UUID는" + procurementPlanListend.get(i).getPurchase().getOrdercode());
            }

        }

        uuidlist.forEach(x-> System.out.println("//////////////////////////////////////최종으로 들어있는 리스트//////////////////////////////////"+x));

    }
}
