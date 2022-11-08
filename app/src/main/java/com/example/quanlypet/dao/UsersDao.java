package com.example.quanlypet.dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.UsersObj;

import java.util.List;

public interface UsersDao {
    @Insert
    void insert(UsersObj object);

    @Query("SELECT * FROM Users")
    List<UsersObj> getAllData();

    @Update
    void edit(UsersObj object);

    @Query("DELETE FROM Users WHERE id = :ID")
    void deleteById(int ID);
}
