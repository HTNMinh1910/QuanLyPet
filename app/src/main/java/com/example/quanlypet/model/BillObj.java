package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bill")
public class BillObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_case_file;
    private String time;
    private double price;
    private String note;
    private int status_obj;

    public BillObj() {
    }

    public BillObj(int id_case_file, String time, double price, String note, int status_obj) {
        this.id_case_file = id_case_file;
        this.time = time;
        this.price = price;
        this.note = note;
        this.status_obj = status_obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_case_file() {
        return id_case_file;
    }

    public void setId_case_file(int id_case_file) {
        this.id_case_file = id_case_file;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus_obj() {
        return status_obj;
    }

    public void setStatus_obj(int status_obj) {
        this.status_obj = status_obj;
    }
}
