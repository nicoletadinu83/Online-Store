package com.sda.onlinestore.dto;

import com.sda.onlinestore.model.OrderLineModel;
import com.sda.onlinestore.model.OrderStatus;
import com.sda.onlinestore.model.UserAdressModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private long id;
    private double totalCost;
    private UserAdressModel deliveryAddress;
    private Date orderDate;
    private List<OrderLineModel> orderLineModel = new ArrayList<>();
    private OrderStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public UserAdressModel getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(UserAdressModel deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderLineModel> getOrderLineModel() {
        return orderLineModel;
    }

    public void setOrderLineModel(List<OrderLineModel> orderLineModel) {
        this.orderLineModel = orderLineModel;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
