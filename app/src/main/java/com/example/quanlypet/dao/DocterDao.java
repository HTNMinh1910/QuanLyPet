package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.DocterObj;

import java.util.List;


@Dao
public interface DocterDao {
    @Insert
    void insert(DocterObj docterObj);

    @Query("SELECT * FROM DocterObj")
    List<DocterObj> getAllData();

    @Update
    void edit(DocterObj docterObj);

}
