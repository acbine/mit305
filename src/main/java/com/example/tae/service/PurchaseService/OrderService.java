package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderDTO;


import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
//    OrderPopupHeadDto getOrderPopupHeadDto(String ordercode);
    List<OrderDTO> getOrderInspectData(int productCode, int procurementPlanCode);

    List<OrderDTO> oListSend();

    void orderRegister(int procurementPlanCode);
    void cancelOrder(int procurementPlanCode);
}
