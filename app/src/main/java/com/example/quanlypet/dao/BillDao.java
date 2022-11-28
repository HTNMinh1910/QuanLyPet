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

    @Query("Select SUM(price) AS 'TongGia' from Bill where date = :dateDT")
    double getPriceDT(String dateDT);

}
