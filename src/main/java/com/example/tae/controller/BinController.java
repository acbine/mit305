package com.example.tae.controller;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class BinController {
    @Autowired
    BinService binService;
    /*--------------------------------------------------입고처리-------------------------------*/
    @GetMapping("ReceivingProcess")
    public String ReceivingProcess(Model model) {
        System.out.println("입고처리 페이지여는 요청들어옴 발주전 태만 보여줘");
        List<ReceivingProcessingDTO> receivingProcessingDTOList = binService.procurementPlanList();
        //receivingProcessingDTOList.forEach(x-> System.out.println(x));
        model.addAttribute("procumentList",receivingProcessingDTOList);
        return "ReceivingProcess";
    }

    @GetMapping("ReceivingProcessSearch")
    public String ReceivingProcessSearch() {
        System.out.println("입고처리의 검색요청들어옴");

        return "ReceivingProcess";
    }
    
    
    @GetMapping("ReceivingProcessStore")
    public String ReceivingProcessStore (@RequestParam("procurementplan_code")String procurementplan_code, @RequestParam("store") String store){
        System.out.println("입고처리 form 형태의 입고처리요청들어옴");
        System.out.println("조달계획번호-------"+procurementplan_code+"입고수량"+store);
       // binService.ReceivingProcessStore(procurementplan_code,store);
        return "total";
    }
    /*------------------------ --------------------현황관리 -----------------------------------*/
    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        System.out.println("현황관리 요청들어옴");
        return "StatusManagementReport";
    }

    @GetMapping("total/StatusManagementReportSearch")
    @ResponseBody
    public ResponseEntity<?> StatusManagementReportSearch(String startDate, String endDate) {
        System.out.println("기간 검색들어옴");
        System.out.println("-----------------------" + startDate + endDate + "--------------------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        List<ReceivingProcessingDTO> statementDTOList = null;
        try {
            Date startLocalDate; //시작 날짜
            Date endLocalDate; //끝날자
            if (startDate.isEmpty()) {
                System.out.println("시작 날짜 없음-----------------");

                startLocalDate = sdf.parse("1000-12-31");
            } else {
                startLocalDate = sdf.parse(startDate);
            }
            if (endDate.isEmpty()) {
                endLocalDate = sdf.parse("5000-12-31");
            } else {
                endLocalDate = sdf.parse(endDate);
            }
            System.out.println("--------------------" + "데이트 타임으로 변환 된것 최종 시작:" + startLocalDate + "------끝-----" + endLocalDate);
            statementDTOList = binService.procurementPlanListbyStatement(startLocalDate, endLocalDate);
            m.addAttribute("stateMentList", statementDTOList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("statementDTOList", statementDTOList));
    }

}
