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
@Table(name = "Blind")
public class Blind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindId;
    private Integer fontsize;
    private Float lineheight;
    private String colorsite;
    private String backsite;

    public Integer getBlindId() {
        return blindId;
    }

    public void setBlindId(Integer blindId) {
        this.blindId = blindId;
    }

    public Integer getFontsize() {
        return fontsize;
    }

    public void setFontsize(Integer fontsize) {
        this.fontsize = fontsize;
    }

    public Float getLineheight() {
        return lineheight;
    }

    public void setLineheight(Float lineheight) {
        this.lineheight = lineheight;
    }

    public String getColorsite() {
        return colorsite;
    }

    public void setColorsite(String colorsite) {
        this.colorsite = colorsite;
    }

    public String getBacksite() {
        return backsite;
    }

    public void setBacksite(String backsite) {
        this.backsite = backsite;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;
}