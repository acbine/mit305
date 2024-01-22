package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderRegisterDto;

import java.util.List;

public interface OrderRegisterService {
    List<OrderRegisterDto> getProcurementPlansWithNullPurchase(String departName);
    void cancelProcurementPlan(int procurementplan_code);
}