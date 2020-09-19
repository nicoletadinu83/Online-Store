package com.sda.onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private List<Product> productList = new ArrayList<>();

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
