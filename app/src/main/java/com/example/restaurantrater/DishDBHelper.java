package com.example.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DishDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dishInfo.db";
    private static final int DATABASE_VER =  1;

    //Database creation SQL statment
    private static final String CREATE_TABLE_INFO =
            "create table contact (_id integer primary key autoincrement), "
            + "dishid int, name text, type text, "
            + "rating text, restaurantid int);";

    public DishDBHelper(Context context) {
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
