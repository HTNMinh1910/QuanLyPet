package com.example.quanlypet.dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.BillObj;

import java.util.List;

public interface BillDao {
    @Insert
    void insert(BillObj object);

    @Query("SELECT * FROM Bill")
    List<BillObj> getAllData();


    @Update
    void edit(BillObj object);

    @Query("DELETE FROM Bill WHERE id = :ID")
    void deleteById(int ID);
}
