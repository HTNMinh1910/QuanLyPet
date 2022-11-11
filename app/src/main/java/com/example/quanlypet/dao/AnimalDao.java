package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.AnimalObj;

import java.util.List;
@Dao
public interface AnimalDao {
    @Insert
    void InsertAnimal(AnimalObj object);
    @Query("SELECT * FROM animal")
    List<AnimalObj> getAllDataAnimal();

    @Update
    void UpDateAnimal( AnimalObj obj);

    @Delete
    void DelteteAnimal( AnimalObj obj);
}
