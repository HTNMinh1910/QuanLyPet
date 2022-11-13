package com.example.quanlypet.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quanlypet.dao.DocterDao;
import com.example.quanlypet.model.DocterObj;


@Database(entities = {DocterObj.class},version = 1)
public abstract class DocterDB extends RoomDatabase {
    private static final String DATABASENAME = "docter1.db";
    private static DocterDB Instance;
    public abstract DocterDao docterDao();

    public static synchronized DocterDB getInstance(Context context){
        if (Instance==null){
            Instance = Room.databaseBuilder(context.getApplicationContext(),DocterDB.class,DATABASENAME).allowMainThreadQueries().build();
        }
        return Instance;
    }
}
