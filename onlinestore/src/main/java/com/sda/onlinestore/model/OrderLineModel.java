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

    private int quantity;

    private double productPrice;

    public long getId() {
        return id;
    }
    
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