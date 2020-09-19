package com.sda.onlinestore.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Order")
@Table
public class OrderModel {

    @Column(name = "userName")
    @OneToOne
    @JoinColumn(name = "login")
    private UserAccountModel userName;

    @Column(name = "totalCost")
    private double totalCost;

    @Column(name = "deliveryAddress")
    @OneToOne
    @JoinColumn(name = "deliveryAdress")
    private UserAdressModel deliveryAddress;

    @Column(name = "userAddress")
    @OneToOne
    @JoinColumn(name = "userAdress")
    private UserAccountModel userAddress;

    @Column(name = "orderDate")
    private Date orderDate;

    private OrderLineModel orderLineModel;

    private UserAccountModel userAccountModel;

    @Column(name = "status")
    @Enumerated
    private OrderStatus status;

    public UserAccountModel getUserName() {
        return userName;
    }

    public void setUserName(UserAccountModel userName) {
        this.userName = userName;
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

    public UserAccountModel getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAccountModel userAddress) {
        this.userAddress = userAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderLineModel getOrderLineModel() {
        return orderLineModel;
    }

    public void setOrderLineModel(OrderLineModel orderLineModel) {
        this.orderLineModel = orderLineModel;
    }

    public UserAccountModel getUserAccountModel() {
        return userAccountModel;
    }

    public void setUserAccountModel(UserAccountModel userAccountModel) {
        this.userAccountModel = userAccountModel;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
