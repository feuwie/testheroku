package com.simakov.diploma.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PersInfo")
public class PersInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int persinfoId;

    private String uuid;

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