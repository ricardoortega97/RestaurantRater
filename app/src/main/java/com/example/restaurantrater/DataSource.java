package com.example.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DataSource {

    private SQLiteDatabase database;
    private restaurantDBHelper dbHelper;
    private DishDBHelper dishDBHelper;
    public DataSource(Context context) {
        dbHelper = new restaurantDBHelper(context);
        dishDBHelper = new DishDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        database = dishDBHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
        dishDBHelper.close();
    }
}
