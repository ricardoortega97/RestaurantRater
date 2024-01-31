package com.example.restaurantrater;

import android.content.ContentValues;
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
    public boolean insertResttaurant(restaurant r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("restaurantid", r.getRestaurantid());
            initialValues.put("name", r.getName());
            initialValues.put("streetaddress", r.getStreetaddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zipcode", r.getZipcode());

            didSucceed = database.insert("resturant", null, initialValues) > 0 ;
        }
        catch (Exception e) {
        }
        return didSucceed;
    }
    public boolean insertDish(dish d) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("dishid", d.getDishid());
            initialValues.put("name", d.getName());
            initialValues.put("type", d.getType());
            initialValues.put("rating", d.getRating());
            initialValues.put("restaurant", d.getRestaurantid());

            didSucceed = database.insert("dish", null, initialValues) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }
}
