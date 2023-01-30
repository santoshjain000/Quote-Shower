package com.example.google_billing.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;



@Dao
public interface Quote_DAO {

    // below method is use to
    // add data to database.
    @Insert
    void insertQuote(room_data_modal modal);

    // below method is use to update
    // the data in our database.
    @Update
    void update(room_data_modal modal);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(room_data_modal modal);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM Quote_table ")
    void deleteMeds();

    @Query("DELETE FROM Quote_table WHERE id = :id")
    void delMeds(int id);


    @Query("SELECT * FROM Quote_table WHERE pageid = :page_id ORDER BY id ASC")
    List<room_data_modal> getAllQuotes(int page_id);

    @Query("SELECT COUNT(*) FROM Quote_table WHERE pageid = :id ORDER BY id ASC")
    int getQuotesCount(int id);




}
