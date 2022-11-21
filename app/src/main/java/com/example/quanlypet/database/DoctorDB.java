package com.example.quanlypet.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quanlypet.dao.DoctorDao;
import com.example.quanlypet.model.DoctorObj;


@Database(entities = {DoctorObj.class},version = 1)
public abstract class DoctorDB extends RoomDatabase {
    private static final String DATABASENAME = "Doctor.db";
    private static DoctorDB Instance;
    public abstract DoctorDao Dao();

    public static synchronized DoctorDB getInstance(Context context){
        if (Instance==null){
            Instance = Room.databaseBuilder(context, DoctorDB.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}
