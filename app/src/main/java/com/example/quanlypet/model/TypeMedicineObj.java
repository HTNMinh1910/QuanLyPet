package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TypeMedicine")
public class TypeMedicineObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int status_obj;

    public TypeMedicineObj() {
    }

    public TypeMedicineObj(String name, int status_obj) {
        this.name = name;
        this.status_obj = status_obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
