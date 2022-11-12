package com.example.quanlypet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlypet.model.BillObj;

import java.util.List;
@Dao
public interface BillDao {
    @Insert
    void insertBill(BillObj object);

    @Query("SELECT * FROM Bill")
    List<BillObj> getAllDataBill();

    @Update
    void editBill(BillObj object);

    @Query("DELETE FROM Bill WHERE id = :ID")
    void deleteById(int ID);
}
