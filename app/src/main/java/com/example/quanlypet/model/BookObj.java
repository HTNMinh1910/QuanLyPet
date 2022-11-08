package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Book")
public class BookObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_doctor;
    private int id_dnimal;
    private String status;
    private int photo_status;
    private String time;
    private String location;
    private String address;
    private String service;
    private int status_obj;

    public BookObj() {
    }

    public BookObj(int id_doctor, int id_dnimal, String status, int photo_status, String time, String location, String address, String service, int status_obj) {
        this.id_doctor = id_doctor;
        this.id_dnimal = id_dnimal;
        this.status = status;
        this.photo_status = photo_status;
        this.time = time;
        this.location = location;
        this.address = address;
        this.service = service;
        this.status_obj = status_obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_dnimal() {
        return id_dnimal;
    }

    public void setId_dnimal(int id_dnimal) {
        this.id_dnimal = id_dnimal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhoto_status() {
        return photo_status;
    }

    public void setPhoto_status(int photo_status) {
        this.photo_status = photo_status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getStatus_obj() {
        return status_obj;
    }

    public void setStatus_obj(int status_obj) {
        this.status_obj = status_obj;
    }
}
