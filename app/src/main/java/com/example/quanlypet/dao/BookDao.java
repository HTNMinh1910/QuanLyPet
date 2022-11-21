package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.BookObj;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(BookObj object);

    @Query("SELECT * FROM Book")
    List<BookObj> getAllData();
    @Query("SELECT * FROM Book where id_user =:id")
    List<BookObj> getAllDataFromID(int id);

    @Update
    void edit(BookObj object);

    @Query("DELETE FROM Book WHERE id = :ID")
    void deleteById(int ID);

    @Query("SELECT *FROM book where id=:id")
    BookObj getIDBook(String id);

}
