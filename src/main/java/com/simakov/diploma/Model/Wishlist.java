package com.simakov.diploma.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.Data;

//@Data
@Entity
@Table(name = "Wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;
    private String uuid;
    private Date wishAdded;

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getWishAdded() {
        return wishAdded;
    }

    public void setWishAdded(Date wishAdded) {
        this.wishAdded = wishAdded;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    private int productId;
    // private String productImg;
    // private String productTitle;
    // private int productPrice;
    // private int productArticle;
    // private int productQuantity;
}