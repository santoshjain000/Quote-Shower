package com.example.google_billing.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(
        version = 1,
        entities = {room_data_modal.class}, exportSchema = true
)
public abstract class Local_Database extends RoomDatabase {

    public abstract Quote_DAO Quote_DAO();


}

