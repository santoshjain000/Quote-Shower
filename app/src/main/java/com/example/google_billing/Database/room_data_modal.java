package com.example.google_billing.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Quote_table")
public class room_data_modal {


    @ColumnInfo(name = "pageid")
    private int pageid;
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int id;



    @ColumnInfo(name = "Quote_id")
    private String Quote_id;


    @ColumnInfo(name = "Author_Name")
    private String Author_Name;




    @ColumnInfo(name = "Quote")
    private String Quote;


    @ColumnInfo(name = "Quote_tag")
    private String Quote_tag;

    @ColumnInfo(name = "authorSlug")
    private String authorSlug;

    @ColumnInfo(name = "length")
    private int length;

    @ColumnInfo(name = "dateAdded")
    private String dateAdded;

    @ColumnInfo(name = "dateModified")
    private String dateModified;

    public room_data_modal(int pageid,  String quote_id, String author_Name,
                           String quote, String quote_tag, String authorSlug,
                           int length, String dateAdded, String dateModified) {
        this.pageid = pageid;
        Quote_id = quote_id;
        Author_Name = author_Name;
        Quote = quote;
        Quote_tag = quote_tag;
        this.authorSlug = authorSlug;
        this.length = length;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }

    public room_data_modal(){}

    public room_data_modal(int pageid, String quote_id, String author_Name, String quote) {
        this.pageid = pageid;
        Quote_id = quote_id;
        Author_Name = author_Name;
        Quote = quote;
    }




    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote_id() {
        return Quote_id;
    }

    public void setQuote_id(String quote_id) {
        Quote_id = quote_id;
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }

    public String getQuote_tag() {
        return Quote_tag;
    }

    public void setQuote_tag(String quote_tag) {
        Quote_tag = quote_tag;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
