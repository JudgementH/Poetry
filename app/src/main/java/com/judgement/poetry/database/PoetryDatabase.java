package com.judgement.poetry.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Poetry.class},version = 1,exportSchema = false)
public abstract class PoetryDatabase extends RoomDatabase {
    private static PoetryDatabase INSTANCE;


    public static synchronized PoetryDatabase getINSTANCE(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, PoetryDatabase.class,"poetry_database")
                    .allowMainThreadQueries()
                    .createFromAsset("poetry_database.db")
                    .build();
        }
        return INSTANCE;
    }

    public abstract PoetryDao getPoetryDao();
}
