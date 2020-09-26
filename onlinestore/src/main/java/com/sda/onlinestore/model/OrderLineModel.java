package com.sda.onlinestore.model;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private ProductModel productModel;

   // @ManyToOne
    //private OrderModel order;

    private int quantity;

    private double productPrice;

    //Getters and Setters

    public long getId() {
        return id;
    }

//    public OrderModel getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderModel order) {
//        this.order = order;
//    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double price) {
        this.productPrice = price;
    }
}