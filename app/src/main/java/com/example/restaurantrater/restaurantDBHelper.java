package com.example.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class restaurantDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VER =  1;

    //Database creation SQL statment
    private static final String CREATE_TABLE_RESTAURANT =
            "CREATE TABLE restaurant (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "restaurantid INTEGER, name TEXT, "
                    + "streetaddress TEXT, city TEXT, "
                    + "state TEXT, zipcode TEXT);";

    public restaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        Log.w(restaurantDBHelper.class.getName(),
                "Upgrading database from verson " + oldVer + " to "
                + newVer + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);
    }
}
