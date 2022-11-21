package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.DoctorObj;

import java.util.List;


@Dao
public interface DoctorDao {
    @Insert
    void insert(DoctorObj docterObj);

    @Query("SELECT * FROM Doctor")
    List<DoctorObj> getAllData();

    @Query("SELECT * FROM Doctor")
    DoctorObj getAll();

    @Update
    void edit(DoctorObj docterObj);

    @Query("SELECT *FROM doctor where id=:id ")
    DoctorObj getIdDoctor(String id);

}
