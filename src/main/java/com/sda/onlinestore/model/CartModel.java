package com.sda.onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private List<OrderLineModel> orderLineModelList = new ArrayList<>();

    public List<OrderLineModel> getOrderLineModelList() {
        return orderLineModelList;
    }

    public void setOrderLineModelList(List<OrderLineModel> orderLineModelList) {
        this.orderLineModelList = orderLineModelList;
    }
}
