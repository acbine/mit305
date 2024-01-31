package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderDTO;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;
import com.example.tae.entity.dto.ImageDTO;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
//    OrderPopupHeadDto getOrderPopupHeadDto(String ordercode);
    List<OrderDTO> getOrderInspectData(int productCode, int procurementPlanCode,int orderIndex);

    List<OrderDTO> oListSend();

    void putOrderPlan(int procurementPlanCode, int num);
    void orderRegister(int procurementPlanCode);
    void cancelOrder(int procurementPlanCode);

    List<OrderDTO> getOrderListWithDate(LocalDateTime date1, LocalDateTime date2);

    OrderDTO getOrderPopup(int procurementPlanCode);

    void orderUpload(@RequestBody ImageDTO imageDTO);
}
