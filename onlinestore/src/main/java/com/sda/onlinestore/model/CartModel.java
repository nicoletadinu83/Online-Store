package com.sda.onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel {

    private List<ProductModel> productList = new ArrayList<>();

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}
