package com.simakov.diploma.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.Data;

//@Data
@Entity
@Table(name = "PersInfo")
public class PersInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int persinfoId;

    private String uuid;

    public int getPersinfoId() {
        return persinfoId;
    }

    public void setPersinfoId(int persinfoId) {
        this.persinfoId = persinfoId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDoorphone() {
        return doorphone;
    }

    public void setDoorphone(int doorphone) {
        this.doorphone = doorphone;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // @Column(nullable = true)
    // private Date dob;
    @Column(nullable = true)
    private String fullname;
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String phone;
    // @Column(nullable = true)
    // private String gender;
    @Column(nullable = true)
    private int doorphone;
    @Column(nullable = true)
    private int floor;
    @Column(nullable = true)
    private int door;
    @Column(nullable = true)
    private int ind;
    @Column(nullable = true)
    private int flat;
    @Column(nullable = true)
    private String address;
    private int orderId;
}