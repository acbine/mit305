package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderListDto;
import com.example.tae.entity.Order.dto.OrderPopupDto;
import com.example.tae.entity.Order.dto.OrderPopupHeadDto;

import java.util.List;

public interface OrderService {
    List<OrderListDto> getAllOrders();
    OrderPopupHeadDto getOrderPopupHeadDto(String ordercode);
    OrderPopupDto getOrderPopupData(String ordercode);
}