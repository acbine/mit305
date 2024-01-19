package com.example.tae.controller;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.service.PurchaseService.OrderRegisterService;
import com.example.tae.service.PurchaseService.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRegisterService orderRegisterService;

//    @PostMapping("/sad")
//    @ResponseBody
//    public ResponseEntity<?> res() {
//        return ResponseEntity.status(HttpStatus.OK).body();
//    }

    @GetMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("oList", orderService.getAllOrders());
        return "orderList";
    }

    @GetMapping("orderListPopup")
    public String orderListPopup(@RequestParam("ordercode") String ordercode, Model model) {
        model.addAttribute("orderPopupDto", orderService.getOrderPopupData(ordercode));
        return "orderListPopup";
    }

    @GetMapping("/orderRegister")
    public String orderRegister(Model model, @RequestParam(required = false) String departName) {
        model.addAttribute("oList", orderRegisterService.getProcurementPlansWithNullPurchase(departName));
        return "orderRegister";
    }

    @GetMapping("/api/orderRegister/count")
    @ResponseBody
    public long getProcurementPlanCountWithNullPurchase(@RequestParam(required = false) String departName) {
        return orderRegisterService.countProcurementPlansWithNullPurchase(departName);
    }

    @DeleteMapping("/api/cancelOrder/{procurementplan_code}")
    @ResponseBody
    public ResponseEntity<Void> cancelOrder(@PathVariable int procurementplan_code) {
        orderRegisterService.cancelProcurementPlan(procurementplan_code);
        return ResponseEntity.noContent().build();
    }
}