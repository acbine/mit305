package com.example.tae.controller;

import com.example.tae.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BinController {
    @Autowired
    BinService binService;
    /*--------------------------------------------------입고처리-------------------------------*/
    @GetMapping("ReceivingProcess")
    public String ReceivingProcess() {
        System.out.println("입고처리 페이지여는 요청들어옴");
        return "ReceivingProcess";
    }

    @GetMapping("ReceivingProcessSearch")
    public String ReceivingProcessSearch() {
        System.out.println("입고처리의 검색요청들어옴");
        return "ReceivingProcess";
    }
    
    
    @GetMapping("ReceivingProcessStore")
    public String ReceivingProcessStore (String 품목코드, String 품목명, String 업체명, String 사업자번호, String 발주서발행일, String 발주일,  String 입고예정일 , String 입고일, String 조달예정수량, String 입고수량 , String 품목처리상태){
        System.out.println("입고처리 form 형태의 입고처리요청들어옴");
        System.out.println(품목코드+품목명+업체명+사업자번호+발주서발행일+발주일+입고예정일+입고일+조달예정수량+입고수량+품목처리상태);
        int 품목입고수량int=Integer.parseInt(입고수량);
        binService.ReceivingProcessStore(품목입고수량int);
        return "ReceivingProcess";
    }
    /*------------------------ --------------------현황관리 -----------------------------------*/
    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        return "StatusManagementReport";
    }

}
