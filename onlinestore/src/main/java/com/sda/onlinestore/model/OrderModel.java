package com.sda.onlinestore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "totalCost")
    private double totalCost;

    @ManyToOne
    private UserAdressModel deliveryAddress;

    @ManyToOne
    private UserAccountModel userAccountModel;

    @Column(name = "orderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany
    private List<OrderLineModel> orderLineModel = new ArrayList<>();

    @Column(name = "status")
    @Enumerated
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

    public UserAccountModel getUserAccountModel() {
        return userAccountModel;
    }

    public void setUserAccountModel(UserAccountModel userAccountModel) {
        this.userAccountModel = userAccountModel;
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
