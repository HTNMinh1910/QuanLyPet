package com.example.quanlypet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Patient")
public class PatientObj {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_doctor;
    private int id_animal;
    private String date_create;
    private String date_update;
    private int status_obj;

    public PatientObj() {
    }

    public PatientObj(int id_doctor, int id_animal, String date_create, String date_update, int status_obj) {
        this.id_doctor = id_doctor;
        this.id_animal = id_animal;
        this.date_create = date_create;
        this.date_update = date_update;
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

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public int getStatus_obj() {
        return status_obj;
    }

    public void setStatus_obj(int status_obj) {
        this.status_obj = status_obj;
    }
}
