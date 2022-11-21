package com.example.quanlypet.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.quanlypet.dao.DoctorDao;
import com.example.quanlypet.model.DoctorObj;


@Database(entities = {DoctorObj.class},version = 2)
public abstract class DoctorDB extends RoomDatabase {
    private static final String DATABASENAME = "Doctor.db";
    private static DoctorDB Instance;
    public abstract DoctorDao docterDao();
    static Migration migration = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static synchronized DoctorDB getInstance(Context context){
        if (Instance==null){
            Instance = Room.databaseBuilder(context, DoctorDB.class,DATABASENAME).allowMainThreadQueries()
                    .addMigrations(migration)
                    .build();
        }
        return Instance;
    }
}
