package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Admin")
public class AdminObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String import_name;
    private String full_name;
    private String email;
    private int status_obj;

    public AdminObj() {
    }

    public AdminObj(String import_name, String full_name, String email, int status_obj) {
        this.import_name = import_name;
        this.full_name = full_name;
        this.email = email;
        this.status_obj = status_obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImport_name() {
        return import_name;
    }

    public void setImport_name(String import_name) {
        this.import_name = import_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus_obj() {
        return status_obj;
    }

    public void setStatus_obj(int status_obj) {
        this.status_obj = status_obj;
    }
}
