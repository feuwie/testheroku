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
@Table(name = "Blind")
public class Blind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindId;
    private Integer fontsize;
    private Float lineheight;
    private String colorsite;
    private String backsite;
    private String uuid;
}