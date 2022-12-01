package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class DSDoctorObj {
    private String name;
    private int img;
    private String phone;

    public DSDoctorObj() {
    }

    public DSDoctorObj(String name, int img, String phone) {
        this.name = name;
        this.img = img;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
