package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.AdminObj;

import java.util.List;
@Dao
public interface AdminDao {
    @Insert
    void insert(AdminObj object);

    @Query("SELECT * FROM Admin")
    List<AdminObj> getAllData();


    @Update
    void edit(AdminObj object);

    @Query("DELETE FROM Admin WHERE id = :ID")
    void deleteById(int ID);
}
