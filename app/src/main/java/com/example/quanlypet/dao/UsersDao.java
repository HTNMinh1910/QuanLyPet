package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.UsersObj;

import java.util.List;
@Dao
public interface UsersDao {
    @Insert
    void insert(UsersObj object);

    @Query("SELECT * FROM Users")
    List<UsersObj> getAllData();

    @Update
    void edit(UsersObj object);

    @Query("SELECT * FROM Users WHERE import_name = :user AND password = :password")
    int checkLogin(String user, String password);

    @Query("UPDATE Users SET password = :pass WHERE phone like :phone")
    void changePass(String phone, String pass);

    @Query("Select * From Users where  import_name = :id")
    UsersObj getIdUsers(String id);
}
