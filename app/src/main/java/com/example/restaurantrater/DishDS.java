package com.example.restaurantrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DishDS {

    private SQLiteDatabase database;
    private DishDBHelper dishDBHelper;

    public DishDS(Context context) {
        dishDBHelper = new DishDBHelper(context);
    }

    public void open() throws SQLException {
        database = dishDBHelper.getWritableDatabase();
    }

    public void close() {
        dishDBHelper.close();
    }

    public boolean insertDish(dish d) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("dishid", d.getDishid());
            initialValues.put("dishname", d.getName());
            initialValues.put("type", d.getType());
            initialValues.put("rating", d.getRating());

            didSucceed = database.insert("dish", null, initialValues) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateDish(dish d) {
        boolean didSuccceed = false;
        try {
            long rowID = d.getDishid();
            ContentValues updateValues = new ContentValues();
            updateValues.put("dishname", d.getName());
            updateValues.put("type", d.getType());
            updateValues.put("rating", d.getRating());

            didSuccceed = database.update("dish", updateValues, "dishid=" + rowID,
                    null) > 0;
        } catch (Exception e) {

        }
        return didSuccceed;
    }
    public int getLastContactID() {
        int lastID;
        try {
            String query = "Select MAX(_id) from dish";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastID = -1;
        }
        return lastID;
    }

}
