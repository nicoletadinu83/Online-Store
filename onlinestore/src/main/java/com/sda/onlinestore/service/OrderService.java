package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto getOrder(Long id);

    List<OrderDto> getAllOrders();

    void deleteOrder(Long id);

    void addOrder(OrderDto orderDto);

    void updateOrder(OrderDto orderDto);
}
