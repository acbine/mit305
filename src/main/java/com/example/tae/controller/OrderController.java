package com.example.tae.controller;

import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;

import com.example.tae.service.PurchaseService.OrderRegisterService;
import com.example.tae.service.PurchaseService.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderRegisterService orderRegisterService;
    private final ProcurementPlanRepository procurementPlanRepository;

    @GetMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("oList", orderService.getAllOrders());
        return "orderList";
    }

    @GetMapping("/orderListPopup")
    public String orderListPopup(@RequestParam(name = "ordercode")String ordercode, Model model) {
//        model.addAttribute("orderPopupHeadDto", orderService.getOrderPopupHeadDto(ordercode));
        model.addAttribute("orderPopupDto", orderService.getOrderPopupData(ordercode));
        return "orderListPopup";
    }

    @GetMapping("/orderInspect/{ordercode}")
    public String orderInspect(@PathVariable String ordercode, Model model){
        //model.addAttribute("orderDetails", orderService.getOrderInspection(ordercode));
        return "orderInspect";
    }

    @GetMapping("/orderInspect1_1")
    public String orderIns1(){
        return "orderInspect1_1";
    }

    @GetMapping("/orderInspect1_2")
    public String orderIns2(){
        return "orderInspect1_2";
    }

    @GetMapping("/orderInspect4")
    public String orderIns4(){
        return "orderInspect4";
    }

//    @RequestParam("departName")String departName
    @GetMapping("orderRegister")
    public String orderRegister(Model model) {
    List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findByProcurementPlanState();
    List<OrderDTO> oList = new ArrayList<>();

    for(ProcurementPlan procurementPlan : procurementPlanList) {
        OrderDTO orderDTO = OrderDTO.builder()
                .departName(procurementPlan.getContract().getCompany().getDepartName())
                .agent(procurementPlan.getContract().getCompany().getBusinessName())
                .email(procurementPlan.getContract().getCompany().getBusinessEmail())
                .fax(procurementPlan.getContract().getCompany().getFax())
                .LT(procurementPlan.getContract().getLead_time())
                .num(procurementPlan.getProjectPlan().getOutPuteNum()*procurementPlan.getProductForProject().getProductCodeCount())
                .tel(procurementPlan.getContract().getCompany().getBusinessTel())
                .orderDate(procurementPlan.getOrder_date())
                .projectOutPutDate(procurementPlan.getProjectPlan().getProjectOutputDate())
                .productCode(procurementPlan.getContract().getProductInformationRegistration().getProduct_code())
                .productName(procurementPlan.getContract().getProductInformationRegistration().getProduct_name())
                .build();
       oList.add(orderDTO);
    }

    model.addAttribute("oList", oList);
    oList.forEach(System.out::println);
    return "orderRegister";
    }

    @GetMapping("orderRegisterData")
    public String orderRegisterData() {
//        model.a""ddAttribute("oList", orderRegisterService.getProcurementPlansWithNullPurchase(departName));
//        List<OrderRegisterDto> oList = orderRegisterService.getProcurementPlansWithNullPurchase(departName);
//        model.addAttribute("departName", departName);
        return "orderRegister";
    }

    @DeleteMapping("/api/cancelOrder/{procurementplan_code}")
    @ResponseBody
    public ResponseEntity<Void> cancelOrder(@PathVariable int procurementplan_code) {
        orderRegisterService.cancelProcurementPlan(procurementplan_code);
        return ResponseEntity.noContent().build();
    }
}
