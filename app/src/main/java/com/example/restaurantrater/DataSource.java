package com.example.restaurantrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DataSource {

    private SQLiteDatabase database;
    private restaurantDBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new restaurantDBHelper(context);

    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();

    }
    public void close() {
        dbHelper.close();

    }
    public boolean insertRestaurant(restaurant r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("restaurantid", r.getRestaurantid());
            initialValues.put("name", r.getName());
            initialValues.put("streetaddress", r.getStreetaddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zipcode", r.getZipcode());

            didSucceed = database.insert("restaurant", null, initialValues) > 0 ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }
    public boolean updateRestaurant(restaurant r) {
        boolean didSucceed = false;
        try {
            long rowID = r.getRestaurantid();
            ContentValues updateValues = new ContentValues();

            updateValues.put("name", r.getName());
            updateValues.put("streetaddress", r.getStreetaddress());
            updateValues.put("city", r.getCity());
            updateValues.put("state", r.getState());
            updateValues.put("zipcode", r.getZipcode());

            didSucceed = database.update("restaurant", updateValues, "restaurantid=" +
                    rowID, null) > 0;
        }
        catch (Exception e){

        }
        return didSucceed;
    }
    public int getLastContactID(){
        int lastID;
        try {
            String query = "Select MAX(_id) from restaurant";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastID = -1;
        }
        return lastID;
    }
}
