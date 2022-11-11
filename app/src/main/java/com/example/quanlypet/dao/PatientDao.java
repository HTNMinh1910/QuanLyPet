package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.PatientObj;

import java.util.List;
@Dao
public interface PatientDao {
    @Insert
    void insert(PatientObj object);

    @Query("SELECT * FROM Patient")
    List<PatientObj> getAllData();

    @Update
    void edit(PatientObj object);

    @Query("DELETE FROM Patient WHERE id = :ID")
    void deleteById(int ID);
}
