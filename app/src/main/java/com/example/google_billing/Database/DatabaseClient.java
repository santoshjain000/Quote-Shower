package com.example.google_billing.Database;

import android.content.Context;

import androidx.room.Room;




public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    Local_Database appDatabase;


    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, Local_Database.class, "LocalDatabase")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public Local_Database getAppDatabase() {
        return appDatabase;
    }
}

