package com.example.quanlypet.dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.AnimalObj;

import java.util.List;

public interface AnimalDao {
    @Insert
    void insert(AnimalObj object);

    @Query("SELECT * FROM Animal")
    List<AnimalObj> getAllData();

    @Update
    void edit(AnimalObj object);

    @Query("DELETE FROM Animal WHERE id = :ID")
    void deleteById(int ID);
}
