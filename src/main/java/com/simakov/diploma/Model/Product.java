package com.simakov.diploma.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productImg;
    private String productTitle;
    private int productPrice;
    // private String productAbout;
    private int productArticle;
    private int productQuantity;
    private int parentId;
    private String rating;
}