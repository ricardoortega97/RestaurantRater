package com.example.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class restaurantDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurantInfo.db";
    private static final int DATABASE_VER =  1;

    //Database creation SQL statment
    private static final String CREATE_TABLE_INFO =
            "create table info (_id integer primary key autoincrement,"
            + "restaurantid int not null, name text, "
            + "streetaddress text, city text, "
            + "state text, zipcode text;";

    public restaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INFO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        Log.w(restaurantDBHelper.class.getName(),
                "Upgrading database from verson " + oldVer + " to "
                + newVer + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}
