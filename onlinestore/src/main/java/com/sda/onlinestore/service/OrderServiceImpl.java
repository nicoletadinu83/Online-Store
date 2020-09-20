package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.model.OrderModel;
import com.sda.onlinestore.repository.CategoryRepository;
import com.sda.onlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto getOrder(Long id) {
        Optional<OrderModel> orderModelOptional = orderRepository.findById(id);

        if (orderModelOptional.isPresent()) {
            OrderModel orderModel = orderModelOptional.get();

            OrderDto orderDto = new OrderDto();

            orderDto.setId(orderModel.getId());
            orderDto.setDeliveryAddress(orderModel.getDeliveryAddress());
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setOrderLineModel(orderModel.getOrderLineModel());
            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());
            orderDto.setUserAccountModel(orderModel.getUserAccountModel());


            return orderDto;
        }
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderModel.getId());
            orderDto.setDeliveryAddress(orderModel.getDeliveryAddress());
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setOrderLineModel(orderModel.getOrderLineModel());
            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());
            orderDto.setUserAccountModel(orderModel.getUserAccountModel());

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void addOrder(OrderDto orderDto) {
        OrderModel orderModel = new OrderModel();
        orderModel.setDeliveryAddress(orderDto.getDeliveryAddress());
        orderModel.setOrderDate(orderDto.getOrderDate());
        orderModel.setOrderLineModel(orderDto.getOrderLineModel());
        orderModel.setStatus(orderDto.getStatus());
        orderModel.setTotalCost(orderDto.getTotalCost());
        orderModel.setUserAccountModel(orderDto.getUserAccountModel());

        orderRepository.save(orderModel);
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Optional<OrderModel> foundOrder = orderRepository.findById(orderDto.getId());
        if (foundOrder.isPresent()) {
            OrderModel orderModel = foundOrder.get();
            orderModel.setDeliveryAddress(orderDto.getDeliveryAddress());
            orderModel.setOrderDate(orderDto.getOrderDate());
            orderModel.setOrderLineModel(orderDto.getOrderLineModel());
            orderModel.setStatus(orderDto.getStatus());
            orderModel.setTotalCost(orderDto.getTotalCost());
            orderModel.setUserAccountModel(orderDto.getUserAccountModel());

            orderRepository.save(orderModel);
        }
    }
}