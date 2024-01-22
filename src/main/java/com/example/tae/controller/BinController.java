package com.example.tae.controller;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.entity.StatusManagement.StatusManagementDTO;
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
import java.util.ArrayList;
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
        System.out.println("입고처리 페이지여는 요청들어옴 검수완료상태만 보여줌");
        List<ReceivingProcessingDTO> receivingProcessingDTOList = binService.procurementPlanList();
        //receivingProcessingDTOList.forEach(x-> System.out.println(x));
        model.addAttribute("procumentList",receivingProcessingDTOList);
        return "ReceivingProcess";
    }

    @GetMapping("ReceivingProcessSearch")
    @ResponseBody
    public ResponseEntity<?> ReceivingProcessSearch() {
        System.out.println("입고처리의 검색요청들어옴");

        return null;
    }
    
    
    @GetMapping("ReceivingProcessStore")
    @ResponseBody
    public ResponseEntity<?> ReceivingProcessStore (@RequestParam("procurementplan_code")String procurementplan_code, @RequestParam("store") String store){
        System.out.println("-------- 입고처리요청들어옴---------------");
        System.out.println("-------------------------------조달계획번호:"+procurementplan_code+"-------------------------입고수량:"+store);
        List<ReceivingProcessingDTO> receivingProcessingDTOList = null;
        if(procurementplan_code.isEmpty() || store.isEmpty()  ){
            System.out.println("뭔가 비었음");
        }else{
            binService.ReceivingProcessStore(Integer.parseInt(procurementplan_code),Integer.parseInt(store));
            receivingProcessingDTOList = binService.procurementPlanList();
        }


        return ResponseEntity.status(HttpStatus.OK).body(Map.of("receivingProcessingDTOList", receivingProcessingDTOList));
    }
    /*------------------------ --------------------현황관리 -----------------------------------*/
    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        System.out.println("현황관리 요청들어옴");
        return "StatusManagementReport";
    }

    @GetMapping("StatusManagementReportSearch")
    @ResponseBody
    public ResponseEntity<?> StatusManagementReportSearch(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate, Model model) {
        System.out.println("--발주관리 리포트에서 검색들어옴--------------------");
        //       System.out.println("-----------------------시작날짜-----"+startDate+"끝날짜------"+endDate + "--------------------------");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<ReceivingProcessingDTO> statementDTOList = null;
        List<StatusManagementDTO> statGroupby=null;

        try {
            Date changedStartDate; //Date 타입 시작 날짜
            Date changedEndDate; //Date 타입 끝날자
            if (startDate.isEmpty()) {
                changedStartDate = sdf.parse("1000-12-31");
            } else {
                changedStartDate = sdf.parse(startDate);
            }
            if (endDate.isEmpty()) {
                changedEndDate = sdf.parse("5000-12-31");
            } else {
                changedEndDate = sdf.parse(endDate);
            }
            System.out.println("--------------------데이트 타임으로 변환 된것 시작날짜:" + changedStartDate + "------끝날짜:" + changedEndDate);
            statementDTOList = binService.procurementPlanListbyStatement(changedStartDate, changedEndDate);
            statGroupby = binService.statusManagementDTOList(changedStartDate,changedEndDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("statementDTOList", statementDTOList));
    }

    @GetMapping("groupby")
    @ResponseBody
    public ResponseEntity<?> groupByOrderState(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate, Model model) {
        System.out.println("--발주관리 리포트에서 검색들어옴--------------------");
        //       System.out.println("-----------------------시작날짜-----"+startDate+"끝날짜------"+endDate + "--------------------------");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<StatusManagementDTO> statGroupby=null;

        try {
            Date changedStartDate; //Date 타입 시작 날짜
            Date changedEndDate; //Date 타입 끝날자
            if (startDate.isEmpty()) {
                changedStartDate = sdf.parse("1000-12-31");
            } else {
                changedStartDate = sdf.parse(startDate);
            }
            if (endDate.isEmpty()) {
                changedEndDate = sdf.parse("5000-12-31");
            } else {
                changedEndDate = sdf.parse(endDate);
            }
            System.out.println("--------------------데이트 타임으로 변환 된것 시작날짜:" + changedStartDate + "------끝날짜:" + changedEndDate);
            statGroupby = binService.statusManagementDTOList(changedStartDate,changedEndDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("statGroupby", statGroupby));
    }

}
