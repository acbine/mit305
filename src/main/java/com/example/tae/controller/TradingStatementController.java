package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TradingStatementController {

    @GetMapping("ReceivingProcess")
    public String ReceivingProcess() {
        return "ReceivingProcess";
    }

    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        return "StatusManagementReport";
    }

    @GetMapping("TradingStatement")
    public String TradingStatement() {
        return "TradingStatement";
    }

    @GetMapping("TradingStatementModal")
    public String TradingStatementModal() {
        return "TradingStatementModal";
    }

}
