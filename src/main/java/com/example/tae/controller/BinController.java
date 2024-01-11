package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BinController {
    /*--------------------------------------------------입고처리-------------------------------*/
    @GetMapping("ReceivingProcess")
    public String ReceivingProcess() {
        return "ReceivingProcess";
    }
    /*------------------------ --------------------현황관리 -----------------------------------*/
    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        return "StatusManagementReport";
    }
}
