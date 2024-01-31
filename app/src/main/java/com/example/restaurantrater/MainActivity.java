package com.example.restaurantrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private restaurant currentRestaurant;
    private dish currentDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRateButton();
        currentRestaurant = new restaurant();
        currentDish = new dish();
    }
    //initialize the rate button
    private void initRateButton(){
        Button ratebutton = findViewById(R.id.rateButton);
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creates intent to access the next class option
                Intent intent = new Intent(MainActivity.this,Rating.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivities(new Intent[]{intent});
            }
        });
    }
}