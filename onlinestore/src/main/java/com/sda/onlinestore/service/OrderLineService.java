package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.OrderLineDto;

import java.util.List;

public interface OrderLineService {

    List<OrderLineDto> getOrderLines();

    void addOrderLine(OrderLineDto orderLineDto);

    void deleteOrderLine(Long id);

    void updateOrderLine(OrderLineDto orderLineDto);

}
