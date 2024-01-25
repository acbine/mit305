package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderDTO;


import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
//    OrderPopupHeadDto getOrderPopupHeadDto(String ordercode);
    OrderDTO getOrderInspectData(int productCode);

    List<OrderDTO> oListSend();

    void orderRegister(int procurementPlanCode);
}
