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

    @Update
    void edit(BookObj object);

    @Query("SELECT *FROM book where id=:id")
    BookObj getIDBook(String id);
}
