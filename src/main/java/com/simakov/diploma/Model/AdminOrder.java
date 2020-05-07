package com.simakov.diploma.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import lombok.Data;

// @Data
@Entity
@Table(name = "AdminOrder")
public class AdminOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminorderId;
    private int orderId;

    public int getAdminorderId() {
        return adminorderId;
    }

    public void setAdminorderId(int adminorderId) {
        this.adminorderId = adminorderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getReviewed() {
        return reviewed;
    }

    public void setReviewed(int reviewed) {
        this.reviewed = reviewed;
    }

    // private int productArticle;
    private int productId;
    // private String productImg;
    // private int productPrice;
    // private String productTitle;
    private int productQuantity;
    // private String userEmail;
    private String uuid;
    private int reviewed;
}