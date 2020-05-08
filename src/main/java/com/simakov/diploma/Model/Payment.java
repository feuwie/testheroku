package com.simakov.diploma.Model;

import com.stripe.model.Token;

import lombok.Data;

@Data
public class Payment {
    // private String userEmail;
    private String uuid;
    private String phone;
    private Integer userAmount;
    private Token token;
}