package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.TypeMedicineObj;

import java.util.List;
@Dao
public interface TypeMedicineDao {
    @Insert
    void insert(TypeMedicineObj object);

    @Query("SELECT * FROM TypeMedicine")
    List<TypeMedicineObj> getAllData();

    @Update
    void edit(TypeMedicineObj object);

    @Query("DELETE FROM TypeMedicine WHERE id = :ID")
    void deleteById(int ID);
}
