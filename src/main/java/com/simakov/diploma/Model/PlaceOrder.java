package com.simakov.diploma.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PlaceOrder")
public class PlaceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    // private String userEmail;
    private String uuid;
    private String orderStatus;
    private Date orderDate;
    private int orderCost;
    private String comment;
    // private String phone;
    // private String email;
    private int cancOrder = 0;
    private String paymentId;
}