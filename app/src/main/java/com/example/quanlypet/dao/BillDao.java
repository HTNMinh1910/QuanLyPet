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

    @Query("SELECT * FROM Bill where id_case_file =:id")
    List<BillObj> getAllDataBillFromUserName(int id);


    @Update
    void editBill(BillObj object);

    @Query("Select SUM(price) AS 'TongGia' from Bill where date = :dateDT")
    double getPriceDT(String dateDT);

}
