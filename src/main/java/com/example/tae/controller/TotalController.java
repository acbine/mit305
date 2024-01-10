package com.example.tae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TotalController {
    @GetMapping("total")
    public String total() {
        return "total";
    }

    @GetMapping("ContractRegistration")
    public String ContractRegistration() {
        return "ContractRegistration";
    }

    // 계약서 모달창 요청 매핑
    @GetMapping("ContractRegistrationModal")
    public String ContractRegistrationModal() {
        return "ContractRegistrationModal";
    }

    @GetMapping("existence")
    public String existence() {
        return "existence";
    }

    @GetMapping("orderInspect1_1")
    public String orderInspect1_1() {
        return "orderInspect1_1";
    }

    @GetMapping("orderInspect1_2")
    public String orderInspect1_2() {
        return "orderInspect1_2";
    }

    @GetMapping("orderInspect4")
    public String orderInspect4() {
        return "orderInspect4";
    }

    @GetMapping("orderList")
    public String orderList() {
        return "orderList";
    }

    @GetMapping("orderRegister")
    public String orderRegister() {
        return "orderRegister";
    }

    @GetMapping("ProductSelect")
    public String ProductSelect(){return "ProductSelect";}

    @GetMapping("ProcurementPlanRegistration")
    public String ProcurementPlanRegistration() {
        return "ProcurementPlanRegistration";
    }

    @GetMapping("ProductInformationRegistration")
    public String ProductInformationRegistration(){
        return "ProductInformationRegistration";
    }
/*
    @GetMapping("ReceivingProcess")
    public String ReceivingProcess() {
        return "ReceivingProcess";
    }

    @GetMapping("ReceivingProcessModal")
    public String ReceivingProcessModal() { return "ReceivingProcessModal"; }

    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        return "StatusManagementReport";
    }
*/
    @GetMapping("stockDelivery")
    public String stockDelivery() {
        return "stockDelivery";
    }
/*
    @GetMapping("TradingStatement")
    public String TradingStatement() {
        return "TradingStatement";
    }
    @GetMapping("TradingStatementModal")
    public String TradingStatementModal() {
        return "TradingStatementModal";
    }
*/
}
