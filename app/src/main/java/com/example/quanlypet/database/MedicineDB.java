package com.example.quanlypet.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quanlypet.dao.MedicineDao;
import com.example.quanlypet.model.MedicineObj;

@Database(entities = {MedicineObj.class},version = 1)
public abstract class MedicineDB extends RoomDatabase {
    public abstract MedicineDao Dao();
    public static final String DATABASENAME="Medicine.db";
    public static MedicineDB Instance;
    public static synchronized MedicineDB getInstance(Context context){
        if(Instance ==null){
            Instance = Room.databaseBuilder(context,MedicineDB.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}