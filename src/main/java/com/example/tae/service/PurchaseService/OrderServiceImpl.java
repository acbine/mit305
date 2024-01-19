package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderListDto;
import com.example.tae.entity.Order.dto.OrderPopupDto;
import com.example.tae.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderListDto> getAllOrders() {
        List<OrderListDto> orderList = new ArrayList<>();

        for (Purchase purchase : orderRepository.findAll())
            orderList.add(OrderListDto.OrderListInfo(purchase));

        return orderList;
    }

    @Override
    public OrderPopupDto getOrderPopupData(String ordercode) {
        return OrderPopupDto.OrderPopupDtoInfo(orderRepository.findById(ordercode).orElse(null));
    }
}