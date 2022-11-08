package com.example.quanlypet.dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.DoctorObj;

import java.util.List;

public interface DoctorDao {
    @Insert
    void insert(DoctorObj object);

    @Query("SELECT * FROM Doctor")
    List<DoctorObj> getAllData();

    @Update
    void edit(DoctorObj object);

    @Query("DELETE FROM Doctor WHERE id = :ID")
    void deleteById(int ID);
}
