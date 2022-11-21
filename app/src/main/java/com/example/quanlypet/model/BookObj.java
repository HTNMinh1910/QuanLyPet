package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Book")
public class BookObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_doctor;
    private int id_animal;
    private String status;
    private byte[] photo_status;
    private String time;
    private String location;
    private String address;
    private String service;


    public BookObj() {
    }

    public BookObj(int id_doctor, int id_dnimal, String status, byte[] photo_status, String time, String location, String address, String service) {
        this.id_doctor = id_doctor;
        this.id_animal = id_dnimal;
        this.status = status;
        this.photo_status = photo_status;
        this.time = time;
        this.location = location;
        this.address = address;
        this.service = service;
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

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getPhoto_status() {
        return photo_status;
    }

    public void setPhoto_status(byte[] photo_status) {
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
}
