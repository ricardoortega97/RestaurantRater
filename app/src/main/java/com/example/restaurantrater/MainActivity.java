package com.example.restaurantrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private restaurant currentRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextChangedEvent();
        initRateButton();
        currentRestaurant = new restaurant();
    }
    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode = findViewById(R.id.editZipcode);
        Button buttonRate = findViewById(R.id.rateButton);
        Button buttonSaved = findViewById(R.id.Saved);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);
        buttonRate.setEnabled(enabled);
        buttonSaved.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
    }

    //initialize the rate button
    private void initRateButton(){
        Button ratebutton = findViewById(R.id.rateButton);
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasSuccessful = false ;
                //get the dataSource
                DataSource ds = new DataSource(MainActivity.this);
                try {
                    ds.open();
                    if (currentRestaurant.getRestaurantid() == -1) {
                        wasSuccessful = ds.insertRestaurant(currentRestaurant);
                        int newID = ds.getLastContactID();
                        currentRestaurant.setRestaurantid(newID);
                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }
                if (wasSuccessful) {
                    setForEditing(false);
                }
                //creates intent to access the next class option
                Intent intent = new Intent(MainActivity.this,Rating.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivities(new Intent[]{intent});
            }
        });
    }

    private void initTextChangedEvent() {
        final EditText etName = findViewById(R.id.editName);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setName(etName.getText().toString());
            }
        });
        final EditText etAddress = findViewById(R.id.editAddress);
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setStreetaddress(etAddress.getText().toString());
            }
        });
        final EditText etCity = findViewById(R.id.editCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setCity(etCity.getText().toString());
            }
        });
        final EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setState(etState.getText().toString());
            }
        });
        final EditText etZip = findViewById(R.id.editZipcode);
        etZip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setZipcode(etZip.getText().toString());
            }
        });
    }
}