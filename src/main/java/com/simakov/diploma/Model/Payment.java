package com.simakov.diploma.Model;

import com.stripe.model.Token;

// import lombok.Data;

// @Data
public class Payment {
    // private String userEmail;
    private String uuid;
    private String phone;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(Integer userAmount) {
        this.userAmount = userAmount;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    private Integer userAmount;
    private Token token;
}