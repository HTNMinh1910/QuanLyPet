package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UsersObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String import_name;
    private String full_name;
    private String email;
    private String phone;
    private int gender;
    private String password;
    private int status_obj;

    public UsersObj() {
    }

    public UsersObj(String import_name, String full_name, String email, String phone, int gender, String password, int status_obj) {
        this.import_name = import_name;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus_obj() {
        return status_obj;
    }

    public void setStatus_obj(int status_obj) {
        this.status_obj = status_obj;
    }
}
