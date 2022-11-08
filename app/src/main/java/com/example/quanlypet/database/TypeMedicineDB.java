package com.example.quanlypet.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quanlypet.dao.TypeMedicineDao;
import com.example.quanlypet.model.TypeMedicineObj;

@Database(entities = {TypeMedicineObj.class},version = 1)
public abstract class TypeMedicineDB extends RoomDatabase {
    public abstract TypeMedicineDao Dao();
    public static final String DATABASENAME="TypeMedicine.db";
    public static TypeMedicineDB Instance;
    public static synchronized TypeMedicineDB getInstance(Context context){
        if(Instance ==null){
            Instance = Room.databaseBuilder(context,TypeMedicineDB.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}