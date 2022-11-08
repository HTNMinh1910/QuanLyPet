package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Medicine")
public class MedicineObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_type;
    private int quantity;
    private String name;
    private int status_obj;

    public MedicineObj() {
    }

    public MedicineObj(int id_type, int quantity, String name, int status_obj) {
        this.id_type = id_type;
        this.quantity = quantity;
        this.name = name;
        this.status_obj = status_obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus_obj() {
        return status_obj;
    }

    public void setStatus_obj(int status_obj) {
        this.status_obj = status_obj;
    }
}
