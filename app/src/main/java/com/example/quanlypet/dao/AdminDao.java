package com.example.quanlypet.dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface AdminDao {
    @Insert
    void insert(Object object);

    @Query("SELECT * FROM table")
    List<Object> getAllData();


    @Update
    void edit(Object object);

    @Query("DELETE FROM table WHERE id = :ID")
    void deleteById(int ID);
}
