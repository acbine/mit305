package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;


import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
//    OrderPopupHeadDto getOrderPopupHeadDto(String ordercode);
    List<OrderDTO> getOrderInspectData(int productCode, int procurementPlanCode);

    List<OrderDTO> oListSend();

    void orderRegister(int procurementPlanCode);
    void cancelOrder(int procurementPlanCode);

    List<OrderDTO> getOrderListWithDate(Date date1, Date date2);
}
