package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderListDto;
import com.example.tae.entity.Order.dto.OrderPopupDto;

import java.util.List;

public interface OrderService {
    List<OrderListDto> getAllOrders();
    OrderPopupDto getOrderPopupData(String ordercode);
}