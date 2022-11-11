package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.MedicineObj;

import java.util.List;
@Dao
public interface MedicineDao {
    @Insert
    void insert(MedicineObj object);

    @Query("SELECT * FROM Medicine")
    List<MedicineObj> getAllData();

    @Update
    void edit(MedicineObj object);

    @Query("DELETE FROM Medicine WHERE id = :ID")
    void deleteById(int ID);
}
