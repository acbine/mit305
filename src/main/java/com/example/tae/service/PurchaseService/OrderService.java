package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.Order.dto.OrderListDto;
import com.example.tae.entity.Order.dto.OrderPopupDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;


import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
//    OrderPopupHeadDto getOrderPopupHeadDto(String ordercode);
    OrderPopupDto getOrderPopupData(String orderCode);

    List<OrderDTO> oListSend();

    void orderRegister(int procurementPlanCode);
}
