package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.AnimalObj;

import java.util.List;
@Dao
public interface AnimalDao {
    @Insert
    void insert(AnimalObj object);

    @Query("SELECT * FROM Animal")
    List<AnimalObj> getAllData();


    @Query("SELECT * FROM Animal")
    AnimalObj getAll();

    @Update
    void edit(AnimalObj object);

    @Query("DELETE FROM Animal WHERE id = :ID")
    void deleteById(int ID);
    @Query("SELECT *FROM animal where id =:id ")
    AnimalObj getIDAnimal(String id);
}
